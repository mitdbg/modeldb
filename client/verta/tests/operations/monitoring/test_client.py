# -*- coding: utf-8 -*-

from verta._internal_utils._utils import generate_default_name

class TestClient(object):

    def test_get_or_create_monitored_entity(self, client):
        monitored_entity = client.operations.get_or_create_monitored_entity()

        user_defined_name = generate_default_name()
        another_entity = client.operations.get_or_create_monitored_entity(name=user_defined_name)

        name = monitored_entity.name
        retrieved_entity = client.operations.get_or_create_monitored_entity(name=name)
        assert monitored_entity.id == retrieved_entity.id

    def test_create_ME_in_workspace(self, client):
        ops = client.operations
        team_workspace = "Demos"
        default_workspace = client._conn.get_default_workspace()
        assert team_workspace != default_workspace

        monitored_in_default = ops.get_or_create_monitored_entity()
        assert monitored_in_default.id
        assert monitored_in_default.workspace == default_workspace

        monitored_in_team = ops.get_or_create_monitored_entity(workspace=team_workspace)
        assert monitored_in_team.id
        assert monitored_in_team.workspace == team_workspace

        still_default = client.get_workspace()
        assert still_default == default_workspace
