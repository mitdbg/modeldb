package ai.verta.modeldb.batchProcess;

import ai.verta.common.CollaboratorTypeEnum;
import ai.verta.common.ModelDBResourceEnum.ModelDBServiceResourceTypes;
import ai.verta.common.VisibilityEnum;
import ai.verta.common.WorkspaceTypeEnum;
import ai.verta.modeldb.DatasetVisibilityEnum;
import ai.verta.modeldb.ModelDBConstants;
import ai.verta.modeldb.authservice.AuthServiceUtils;
import ai.verta.modeldb.authservice.RoleService;
import ai.verta.modeldb.authservice.RoleServiceUtils;
import ai.verta.modeldb.common.CommonUtils;
import ai.verta.modeldb.common.authservice.AuthService;
import ai.verta.modeldb.config.Config;
import ai.verta.modeldb.dto.WorkspaceDTO;
import ai.verta.modeldb.entities.ProjectEntity;
import ai.verta.modeldb.entities.versioning.RepositoryEntity;
import ai.verta.modeldb.utils.ModelDBHibernateUtil;
import ai.verta.modeldb.utils.ModelDBUtils;
import ai.verta.uac.CollaboratorPermissions;
import ai.verta.uac.GetResourcesResponseItem;
import ai.verta.uac.ResourceVisibility;
import ai.verta.uac.UserInfo;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollaboratorResourceMigration {
  private static final Logger LOGGER = LogManager.getLogger(CollaboratorResourceMigration.class);
  private static final String REPOSITORY_GLOBAL_SHARING = "_REPO_GLOBAL_SHARING";
  private static AuthService authService;
  private static RoleService roleService;
  private static int paginationSize;

  public CollaboratorResourceMigration() {}

  public static void execute() {
    CollaboratorResourceMigration.paginationSize = 100;
    if (Config.getInstance().hasAuth()) {
      authService = AuthServiceUtils.FromConfig(Config.getInstance());
      roleService = RoleServiceUtils.FromConfig(Config.getInstance(), authService);
    } else {
      LOGGER.debug("AuthService Host & Port not found, OSS setup found");
      return;
    }

    LOGGER.info("Migration start");
    CommonUtils.registeredBackgroundUtilsCount();
    try {
      migrateProjects();
      LOGGER.info("Projects done migration");
      migrateRepositories();
      LOGGER.info("Repositories done migration");
    } finally {
      CommonUtils.unregisteredBackgroundUtilsCount();
    }

    LOGGER.info("Migration End");
  }

  private static void migrateProjects() {
    LOGGER.debug("Projects migration started");
    Long count = getEntityCount(ProjectEntity.class);

    int lowerBound = 0;
    final int pagesize = CollaboratorResourceMigration.paginationSize;
    LOGGER.debug("Total Projects to migrate {}", count);

    Set<String> knownMissingUsers = new HashSet<>();

    try (Session session = ModelDBHibernateUtil.getSessionFactory().openSession()) {
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

      CriteriaQuery<ProjectEntity> criteriaQuery = criteriaBuilder.createQuery(ProjectEntity.class);
      Root<ProjectEntity> root = criteriaQuery.from(ProjectEntity.class);

      CriteriaQuery<ProjectEntity> selectQuery =
          criteriaQuery
              .select(root)
              .where(
                  criteriaBuilder.and(
                      criteriaBuilder.equal(root.get("visibility_migration"), false),
                      criteriaBuilder.equal(root.get("created"), true)));

      TypedQuery<ProjectEntity> typedQuery = session.createQuery(selectQuery);
      Stream<ProjectEntity> projectEntities = typedQuery.getResultStream();
      Iterator<ProjectEntity> iterator = projectEntities.iterator();

      Map<String, UserInfo> userInfoMap = new HashMap<>();

      int counter = 0;
      while (iterator.hasNext()) {
        LOGGER.debug("Migrated Projects: {}/{}", counter, count);
        counter++;

        ProjectEntity project = iterator.next();

        boolean migrated = false;
        if (project.getOwner() != null && !project.getOwner().isEmpty()) {
          WorkspaceDTO workspaceDTO;
          if (knownMissingUsers.contains(project.getOwner())) {
            LOGGER.warn("User {} is known to be missing (skipping)", project.getOwner());
            continue;
          }
          if (!userInfoMap.containsKey(project.getOwner())) {
            try {
              userInfoMap.putAll(
                  authService.getUserInfoFromAuthServer(
                      Collections.singleton(project.getOwner()), null, null));
            } catch (StatusRuntimeException ex) {
              if (ex.getStatus().getCode() == Status.Code.NOT_FOUND) {
                knownMissingUsers.add(project.getOwner());
                LOGGER.warn("Failed to get user info (skipping) : " + ex.toString());
                continue;
              }
              throw ex;
            }
          }
          try {
            workspaceDTO =
                roleService.getWorkspaceDTOByWorkspaceId(
                    userInfoMap.get(project.getOwner()),
                    project.getWorkspace(),
                    project.getWorkspace_type());
          } catch (StatusRuntimeException ex) {
            if (ex.getStatus().getCode() == Status.Code.NOT_FOUND) {
              knownMissingUsers.add(project.getOwner());
              LOGGER.warn("Failed to get workspace (skipping) : " + ex.toString());
              continue;
            }
            throw ex;
          }
          Long owner;
          try {
            owner = Long.parseLong(project.getOwner());
          } catch (NumberFormatException ex) {
            LOGGER.warn("Failed to convert owner (skipping) : " + ex.toString());
            continue;
          }
          // if projectVisibility is not equals to ResourceVisibility.ORG_SCOPED_PUBLIC then
          // ignore the CollaboratorType
          try {
            roleService.createWorkspacePermissions(
                workspaceDTO.getWorkspaceName(),
                project.getId(),
                project.getName(),
                Optional.of(owner),
                ModelDBServiceResourceTypes.PROJECT,
                CollaboratorPermissions.newBuilder()
                    .setCollaboratorType(CollaboratorTypeEnum.CollaboratorType.READ_ONLY)
                    .build(),
                getResourceVisibility(
                    Optional.ofNullable(
                        WorkspaceTypeEnum.WorkspaceType.forNumber(project.getWorkspace_type())),
                    VisibilityEnum.Visibility.forNumber(project.getProject_visibility())));
          } catch (StatusRuntimeException ex) {
            if (ex.getStatus().getCode() == Status.Code.ALREADY_EXISTS) {
              LOGGER.info(
                  "Resource seem to have already been created (ignoring) : " + ex.toString());
            } else {
              throw ex;
            }
          }
          migrated = true;
        } else {
          List<GetResourcesResponseItem> resources =
              roleService.getResourceItems(
                  null,
                  Collections.singleton(project.getId()),
                  ModelDBServiceResourceTypes.PROJECT);
          if (!resources.isEmpty()) {
            GetResourcesResponseItem resourceDetails = resources.get(0);
            roleService.createWorkspacePermissions(
                resourceDetails.getWorkspaceId(),
                Optional.empty(),
                project.getId(),
                project.getName(),
                Optional.of(resourceDetails.getOwnerId()),
                ModelDBServiceResourceTypes.PROJECT,
                resourceDetails.getCustomPermission(),
                resourceDetails.getVisibility());
            migrated = true;
          }
        }
        if (migrated) {
          deleteRoleBindingsForProjects(Collections.singletonList(project));
          try (Session session1 = ModelDBHibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
              transaction = session1.beginTransaction();
              project.setVisibility_migration(true);
              session.update(project);
              transaction.commit();
            } catch (Exception ex) {
              if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
              }
            }
          }
        }
      }
    }

    LOGGER.debug("Projects migration finished");
  }

  private static void migrateRepositories() {
    LOGGER.debug("Repositories migration started");
    Long count = getEntityCount(RepositoryEntity.class);

    int lowerBound = 0;
    final int pagesize = CollaboratorResourceMigration.paginationSize;
    LOGGER.debug("Total Repositories {}", count);

    while (lowerBound < count) {

      try (Session session = ModelDBHibernateUtil.getSessionFactory().openSession()) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<RepositoryEntity> criteriaQuery =
            criteriaBuilder.createQuery(RepositoryEntity.class);
        Root<RepositoryEntity> root = criteriaQuery.from(RepositoryEntity.class);

        CriteriaQuery<RepositoryEntity> selectQuery =
            criteriaQuery
                .select(root)
                .where(
                    criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("visibility_migration"), false),
                        criteriaBuilder.equal(root.get("created"), true)));

        TypedQuery<RepositoryEntity> typedQuery = session.createQuery(selectQuery);

        typedQuery.setFirstResult(lowerBound);
        typedQuery.setMaxResults(pagesize);
        List<RepositoryEntity> repositoryEntities = typedQuery.getResultList();

        if (repositoryEntities.size() > 0) {
          Set<String> userIds = new HashSet<>();
          Set<String> newVisibilityRepositoryIds = new HashSet<>();
          Set<String> newVisibilityDatasetIds = new HashSet<>();
          for (RepositoryEntity repositoryEntity : repositoryEntities) {
            if (repositoryEntity.getOwner() != null && !repositoryEntity.getOwner().isEmpty()) {
              userIds.add(repositoryEntity.getOwner());
            } else {
              if (repositoryEntity.isDataset()) {
                newVisibilityDatasetIds.add(String.valueOf(repositoryEntity.getId()));
              } else {
                newVisibilityRepositoryIds.add(String.valueOf(repositoryEntity.getId()));
              }
            }
          }
          LOGGER.debug("Repository userId list : " + userIds);

          // Fetch the repository owners userInfo
          Map<String, UserInfo> userInfoMap = new HashMap<>();
          if (!userIds.isEmpty()) {
            userInfoMap.putAll(authService.getUserInfoFromAuthServer(userIds, null, null));
          }

          List<GetResourcesResponseItem> responseRepositoryItems =
              roleService.getResourceItems(
                  null, newVisibilityRepositoryIds, ModelDBServiceResourceTypes.REPOSITORY);
          List<GetResourcesResponseItem> responseDatasetItems =
              roleService.getResourceItems(
                  null, newVisibilityDatasetIds, ModelDBServiceResourceTypes.DATASET);
          Set<GetResourcesResponseItem> responseItems = new HashSet<>(responseRepositoryItems);
          responseItems.addAll(responseDatasetItems);

          Map<String, GetResourcesResponseItem> responseItemMap =
              responseItems.stream()
                  .collect(Collectors.toMap(GetResourcesResponseItem::getResourceId, item -> item));
          for (RepositoryEntity repository : repositoryEntities) {
            boolean migrated = false;
            ModelDBServiceResourceTypes modelDBServiceResourceTypes =
                ModelDBUtils.getModelDBServiceResourceTypesFromRepository(repository);
            if (repository.getOwner() != null && !repository.getOwner().isEmpty()) {
              WorkspaceDTO workspaceDTO =
                  roleService.getWorkspaceDTOByWorkspaceId(
                      userInfoMap.get(repository.getOwner()),
                      repository.getWorkspace_id(),
                      repository.getWorkspace_type());
              // if repositoryVisibility is not equals to ResourceVisibility.ORG_SCOPED_PUBLIC then
              // ignore the CollaboratorType
              roleService.createWorkspacePermissions(
                  workspaceDTO.getWorkspaceName(),
                  String.valueOf(repository.getId()),
                  repository.getName(),
                  Optional.of(Long.parseLong(repository.getOwner())),
                  modelDBServiceResourceTypes,
                  CollaboratorPermissions.newBuilder()
                      .setCollaboratorType(CollaboratorTypeEnum.CollaboratorType.READ_ONLY)
                      .build(),
                  getResourceVisibility(
                      Optional.ofNullable(
                          WorkspaceTypeEnum.WorkspaceType.forNumber(
                              repository.getWorkspace_type())),
                      VisibilityEnum.Visibility.forNumber(repository.getRepository_visibility())));
              migrated = true;
            } else if (responseItemMap.containsKey(String.valueOf(repository.getId()))) {
              GetResourcesResponseItem resourceDetails =
                  responseItemMap.get(String.valueOf(repository.getId()));
              roleService.createWorkspacePermissions(
                  resourceDetails.getWorkspaceId(),
                  Optional.empty(),
                  String.valueOf(repository.getId()),
                  repository.getName(),
                  Optional.of(resourceDetails.getOwnerId()),
                  modelDBServiceResourceTypes,
                  resourceDetails.getCustomPermission(),
                  resourceDetails.getVisibility());
              migrated = true;
            }
            if (migrated) {
              Transaction transaction = null;
              try {
                deleteRoleBindingsOfRepositories(Collections.singletonList(repository));
                transaction = session.beginTransaction();
                repository.setVisibility_migration(true);
                session.update(repository);
                transaction.commit();
              } catch (Exception ex) {
                if (transaction != null && transaction.getStatus().canRollback()) {
                  transaction.rollback();
                }
              }
            }
          }
        } else {
          LOGGER.debug("Total repositories count 0");
        }
        lowerBound += pagesize;
      } catch (Exception ex) {
        if (ModelDBUtils.needToRetry(ex)) {
          migrateRepositories();
        } else {
          throw ex;
        }
      }
    }

    LOGGER.debug("Repositories migration finished");
  }

  private static <T> Long getEntityCount(Class<T> klass) {
    try (Session session = ModelDBHibernateUtil.getSessionFactory().openSession()) {
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
      Root<T> root = countQuery.from(klass);
      countQuery
          .select(criteriaBuilder.count(root))
          .where(
              criteriaBuilder.and(
                  criteriaBuilder.equal(root.get("visibility_migration"), false),
                  criteriaBuilder.equal(root.get("created"), true)));

      return session.createQuery(countQuery).getSingleResult();
    } catch (Exception ex) {
      if (ModelDBUtils.needToRetry(ex)) {
        return getEntityCount(klass);
      } else {
        throw ex;
      }
    }
  }

  private static ResourceVisibility getResourceVisibility(
      Optional<WorkspaceTypeEnum.WorkspaceType> workspaceType,
      VisibilityEnum.Visibility visibility) {
    if (!workspaceType.isPresent()) {
      return ResourceVisibility.PRIVATE;
    }
    if (workspaceType.get() == WorkspaceTypeEnum.WorkspaceType.ORGANIZATION) {
      if (visibility == VisibilityEnum.Visibility.ORG_SCOPED_PUBLIC) {
        return ResourceVisibility.ORG_DEFAULT;
      } else if (visibility == VisibilityEnum.Visibility.PRIVATE) {
        return ResourceVisibility.PRIVATE;
      }
      return ResourceVisibility.ORG_DEFAULT;
    }
    return ResourceVisibility.PRIVATE;
  }

  private static void deleteRoleBindingsForProjects(List<ProjectEntity> projectEntities) {
    // set roleBindings name by accessible projects
    List<String> roleBindingNames = new LinkedList<>();
    setRoleBindingsNameOfAccessibleProjectsInRoleBindingNamesList(
        projectEntities, roleBindingNames);
    LOGGER.debug("num bindings after Projects {}", roleBindingNames.size());

    // Remove all role bindings
    if (!roleBindingNames.isEmpty()) {
      roleService.deleteRoleBindings(roleBindingNames);
    }
  }

  private static void setRoleBindingsNameOfAccessibleProjectsInRoleBindingNamesList(
      List<ProjectEntity> allowedProjects, List<String> roleBindingNames) {
    for (ProjectEntity project : allowedProjects) {
      String projectId = project.getId();

      String ownerRoleBindingName =
          roleService.buildRoleBindingName(
              ModelDBConstants.ROLE_PROJECT_OWNER,
              project.getId(),
              project.getOwner(),
              ModelDBServiceResourceTypes.PROJECT.name());
      if (ownerRoleBindingName != null) {
        roleBindingNames.add(ownerRoleBindingName);
      }

      // Delete workspace based roleBindings
      List<String> workspaceRoleBindingNames =
          roleService.getWorkspaceRoleBindings(
              project.getWorkspace(),
              WorkspaceTypeEnum.WorkspaceType.forNumber(project.getWorkspace_type()),
              projectId,
              ModelDBConstants.ROLE_PROJECT_ADMIN,
              ModelDBServiceResourceTypes.PROJECT,
              project.getProjectVisibility().equals(VisibilityEnum.Visibility.ORG_SCOPED_PUBLIC),
              "_GLOBAL_SHARING");
      roleBindingNames.addAll(workspaceRoleBindingNames);
    }
  }

  private static void deleteRoleBindingsOfRepositories(List<RepositoryEntity> allowedResources) {
    final List<String> roleBindingNames = Collections.synchronizedList(new ArrayList<>());
    for (RepositoryEntity repositoryEntity : allowedResources) {
      String ownerRoleBindingName =
          roleService.buildRoleBindingName(
              ModelDBConstants.ROLE_REPOSITORY_OWNER,
              String.valueOf(repositoryEntity.getId()),
              repositoryEntity.getOwner(),
              ModelDBServiceResourceTypes.REPOSITORY.name());
      if (ownerRoleBindingName != null) {
        roleBindingNames.add(ownerRoleBindingName);
      }

      // Delete workspace based roleBindings
      List<String> repoOrgWorkspaceRoleBindings =
          roleService.getWorkspaceRoleBindings(
              repositoryEntity.getWorkspace_id(),
              WorkspaceTypeEnum.WorkspaceType.forNumber(repositoryEntity.getWorkspace_type()),
              String.valueOf(repositoryEntity.getId()),
              ModelDBConstants.ROLE_REPOSITORY_ADMIN,
              ModelDBServiceResourceTypes.REPOSITORY,
              repositoryEntity
                  .getRepository_visibility()
                  .equals(DatasetVisibilityEnum.DatasetVisibility.ORG_SCOPED_PUBLIC_VALUE),
              REPOSITORY_GLOBAL_SHARING);
      if (!repoOrgWorkspaceRoleBindings.isEmpty()) {
        roleBindingNames.addAll(repoOrgWorkspaceRoleBindings);
      }
    }

    // Remove all role bindings
    if (!roleBindingNames.isEmpty()) {
      roleService.deleteRoleBindings(roleBindingNames);
    }
  }
}
