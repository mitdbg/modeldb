from verta._internal_utils import _utils


class TestSharing:
    def test_share_project_personal_workspace(self, client, client_2, email_2):
        project_name = _utils.generate_default_name()
        project = client.create_project(project_name)
        project._add_collaborator(email=email_2)

        assert client_2.get_project(id=project.id)
        assert client_2.get_project(name=project.name)

    def test_share_org_public_project(self, client, organization, client_2, email_2):
        project_name = _utils.generate_default_name()
        project = client.create_project(project_name, workspace=organization.name, public_within_org=True)

        organization.add_member(email_2)
        project._add_collaborator(email=email_2)

        assert client_2.get_project(id=project.id)
        assert client_2.get_project(name=project.name)
