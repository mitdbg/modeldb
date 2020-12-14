package ai.verta.modeldb.authservice;

import ai.verta.common.CollaboratorTypeEnum;
import ai.verta.common.ModelDBResourceEnum;
import ai.verta.common.ModelDBResourceEnum.ModelDBServiceResourceTypes;
import ai.verta.common.VisibilityEnum;
import ai.verta.common.WorkspaceTypeEnum.WorkspaceType;
import ai.verta.modeldb.collaborator.CollaboratorBase;
import ai.verta.modeldb.dto.WorkspaceDTO;
import ai.verta.uac.*;
import ai.verta.uac.ModelDBActionEnum.ModelDBServiceActions;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ProtocolMessageEnum;
import io.grpc.Metadata;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoleService {

  boolean IsImplemented();

  String buildRoleBindingName(
      String roleName, String resourceId, String userId, String resourceTypeName);

  String buildRoleBindingName(
      String roleName, String resourceId, CollaboratorBase collaborator, String resourceTypeName);

  void createRoleBinding(
      Role role,
      CollaboratorBase collaborator,
      String resourceId,
      ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  void createPublicRoleBinding(
      String resourceId, ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  String buildPublicRoleBindingName(
      String resourceId, ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  void isSelfAllowed(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      ModelDBServiceActions modelDBServiceActions,
      String resourceId);

  Map<String, Actions> getSelfAllowedActionsBatch(
      List<String> resourceIds, ModelDBServiceResourceTypes type);

  Role getRoleByName(String roleName, RoleScope roleScope);

  boolean deleteRoleBinding(String roleBindingId);

  boolean deleteRoleBindings(List<String> roleBindingNames);

  List<GetCollaboratorResponseItem> getResourceCollaborators(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      String resourceId,
      String resourceOwnerId,
      Metadata requestHeaders);

  /**
   * Checks permissions of the user wrt the Entity
   *
   * @param resourceId --> value of key like project.id, dataset.id etc.
   * @param modelDBServiceActions --> ModelDBServiceActions.UPDATE, ModelDBServiceActions.DELETE,
   *     ModelDBServiceActions.CREATE
   */
  void validateEntityUserWithUserInfo(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      String resourceId,
      ModelDBServiceActions modelDBServiceActions)
      throws InvalidProtocolBufferException;

  String buildReadOnlyRoleBindingName(
      String resourceId,
      CollaboratorBase collaborator,
      ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  String buildReadWriteRoleBindingName(
      String resourceId,
      CollaboratorBase collaborator,
      ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  String buildProjectDeployRoleBindingName(
      String resourceId,
      CollaboratorBase collaborator,
      ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  RoleBinding getRoleBindingByName(String roleBindingName);

  List<String> getSelfAllowedResources(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      ModelDBServiceActions modelDBServiceActions);

  List<String> getSelfDirectlyAllowedResources(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      ModelDBServiceActions modelDBServiceActions);

  List<String> getAllowedResources(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      ModelDBServiceActions modelDBServiceActions,
      CollaboratorBase collaboratorBase);

  GeneratedMessageV3 getTeamById(String teamId);

  GeneratedMessageV3 getTeamByName(String orgId, String teamName);

  GeneratedMessageV3 getOrgById(String orgId);

  GeneratedMessageV3 getOrgByName(String name);

  List<String> getAccessibleResourceIds(
      CollaboratorBase hostUserInfo,
      CollaboratorBase currentLoginUserInfo,
      ProtocolMessageEnum resourceVisibility,
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      List<String> requestedResourceIds);

  List<String> getAccessibleResourceIdsByActions(
      ModelDBServiceResourceTypes modelDBServiceResourceTypes,
      ModelDBServiceActions modelDBServiceActions,
      List<String> requestedIdList);

  /**
   * from the name for workspace, get the workspace id and type. if no workspace is present assume
   * user's personal workspace
   *
   * @param currentLoginUserInfo : current login userInfo
   * @param workspaceName : orgName or username
   * @return {@link WorkspaceDTO} : workspace dto
   */
  WorkspaceDTO getWorkspaceDTOByWorkspaceName(UserInfo currentLoginUserInfo, String workspaceName);

  WorkspaceDTO getWorkspaceDTOByWorkspaceId(
      UserInfo currentLoginUserInfo, String workspaceId, Integer workspaceType);

  List<Organization> listMyOrganizations();

  boolean createWorkspacePermissions(
      String workspaceName,
      String resourceId,
      Optional<Long> ownerId,
      ModelDBServiceResourceTypes resourceType,
      CollaboratorTypeEnum.CollaboratorType collaboratorType,
      ResourceVisibility resourceVisibility);

  boolean createWorkspacePermissions(
      Long workspaceId,
      Optional<WorkspaceType> workspaceType,
      String resourceId,
      Optional<Long> ownerId,
      ModelDBServiceResourceTypes resourceType,
      CollaboratorTypeEnum.CollaboratorType collaboratorType,
      VisibilityEnum.Visibility projectVisibility);

  void createWorkspacePermissions(
      String workspace_id,
      WorkspaceType forNumber,
      String valueOf,
      String roleRepositoryAdmin,
      ModelDBServiceResourceTypes repository,
      boolean orgScopedPublic,
      String globalSharing);

  List<String> getWorkspaceRoleBindings(
      String workspace_id,
      WorkspaceType workspaceType,
      String resourceId,
      String adminRole,
      ModelDBServiceResourceTypes resourceType,
      boolean orgScopedPublic,
      String globalSharing);

  boolean deleteAllResources(
      List<String> resourceIds, ModelDBServiceResourceTypes modelDBServiceResourceTypes);

  boolean checkConnectionsBasedOnPrivileges(
      ModelDBResourceEnum.ModelDBServiceResourceTypes serviceResourceTypes,
      ModelDBActionEnum.ModelDBServiceActions serviceActions,
      String resourceId);
}
