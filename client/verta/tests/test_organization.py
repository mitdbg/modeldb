import pytest
import json

from verta import Client
from verta._internal_utils import _utils
from verta._protos.public.uac import Organization_pb2 as _Organization
from verta._protos.public.common import CommonService_pb2 as _CommonCommonService
from verta._tracking.organization import Organization, CollaboratorType

from .utils import delete_project


def test_create_msg():
    assert Organization._create_msg("name", "desc", None, None) == \
           _Organization.Organization(name="name", description="desc",
                                      global_can_deploy=_CommonCommonService.TernaryEnum.Ternary.FALSE)
    assert Organization._create_msg("name", "desc", CollaboratorType(
        default_repo_collaborator_type="READ_WRITE",
        default_endpoint_collaborator_type="READ_ONLY"), True) == \
           _Organization.Organization(name="name", description="desc",
                                      global_can_deploy=_CommonCommonService.TernaryEnum.Ternary.TRUE,
                                      default_repo_collaborator_type=
                                      _CommonCommonService.CollaboratorTypeEnum.CollaboratorType.READ_WRITE)
    with pytest.raises(ValueError):
        assert Organization._create_msg("name", "desc", CollaboratorType(
            default_repo_collaborator_type="READ_WRITET",
            default_endpoint_collaborator_type="READ_ONLY"), True)


class TestOrganization:
    @pytest.mark.skip("delete not implemented yet")
    def test_create(self, client):
        name = _utils.generate_default_name()
        assert client._create_organization(name)

    def test_create_same_name_diff_workspace(self, client, organization, in_tempdir, created_endpoints, created_registered_models, created_datasets):
        # creating some entities:
        project_name = _utils.generate_default_name()
        exp_name = _utils.generate_default_name()
        run_name = _utils.generate_default_name()
        dataset_name = _utils.generate_default_name()

        model_name = _utils.generate_default_name()
        version_name = _utils.generate_default_name()

        endpoint_path = _utils.generate_default_name()

        project = client.create_project(project_name)
        exp = client.create_experiment(exp_name)
        run = client.create_experiment_run(run_name)

        dataset = client._create_dataset2(dataset_name)
        created_datasets.append(dataset)

        model = client.create_registered_model(name=model_name)
        version = model.create_version(name=version_name)
        created_registered_models.append(model)

        endpoint = client.create_endpoint(path=endpoint_path)
        created_endpoints.append(endpoint)

        # Switching workspace name:
        client_config = {
            "workspace": organization.name
        }

        filepath = "verta_config.json"
        with open(filepath, "w") as f:
            json.dump(client_config, f)

        client = Client()

        # create entities with same name, but different workspace:
        new_model = client.create_registered_model(name=model_name)
        new_version = new_model.create_version(name=version_name)
        new_endpoint = client.create_endpoint(path=endpoint_path)

        new_project = client.create_project(project_name)
        new_exp = client.create_experiment(exp_name)
        new_run = client.create_experiment_run(run_name)

        new_dataset = client._create_dataset2(dataset_name)
        created_datasets.append(new_dataset)

        created_endpoints.append(new_endpoint)
        created_registered_models.append(new_model)

        assert model.id != new_model.id
        assert version.id != new_version.id
        assert endpoint.id != new_endpoint.id
        assert project.id != new_project.id
        assert exp.id != new_exp.id
        assert run.id != new_run.id
        assert dataset.id != new_dataset.id

        # only need to delete new project:
        delete_project(new_project.id, client._conn)
