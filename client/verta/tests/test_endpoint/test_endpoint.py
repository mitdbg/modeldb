import time

import pytest
import requests

import verta
from verta.deployment.update import DirectUpdateStrategy, CanaryUpdateStrategy
from verta.deployment.update.rules import AverageLatencyThresholdRule
from verta._internal_utils import _utils

def get_build_ids(status):
    # get the set of build_ids in the status of the stage:
    return set(map(lambda comp: comp["build_id"], status["components"]))

class TestEndpoint:
    def test_create(self, client, created_endpoints):
        name = _utils.generate_default_name()
        endpoint = client.set_endpoint(name)
        assert endpoint
        created_endpoints.append(endpoint)
        name = verta._internal_utils._utils.generate_default_name()
        endpoint = client.create_endpoint(name)
        assert endpoint
        with pytest.raises(requests.HTTPError) as excinfo:
            assert client.create_endpoint(name)
        excinfo_value = str(excinfo.value).strip()
        assert "409" in excinfo_value
        assert "already in use" in excinfo_value

    def test_get(self, client, created_endpoints):
        name = _utils.generate_default_name()

        with pytest.raises(ValueError):
            client.get_endpoint(name)

        endpoint = client.set_endpoint(name)
        created_endpoints.append(endpoint)

        assert endpoint.id == client.get_endpoint(endpoint.path).id
        assert endpoint.id == client.get_endpoint(id=endpoint.id).id

    def test_get_by_name(self, client, created_endpoints):
        path = _utils.generate_default_name()
        path2 = _utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        dummy_endpoint = client.set_endpoint(path2)  # in case get erroneously fetches latest
        created_endpoints.append(dummy_endpoint)

        assert endpoint.id == client.set_endpoint(endpoint.path).id

    def test_get_by_id(self, client, created_endpoints):
        path = _utils.generate_default_name()
        path2 = _utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        dummy_endpoint = client.set_endpoint(path2)  # in case get erroneously fetches latest
        created_endpoints.append(dummy_endpoint)

        assert endpoint.id == client.set_endpoint(id=endpoint.id).id

    def test_list(self, client, created_endpoints):
        name = _utils.generate_default_name()
        endpoint = client.set_endpoint(name)
        created_endpoints.append(endpoint)

        endpoints = client.endpoints
        assert len(endpoints) >= 1
        has_new_id = False
        for item in endpoints:
            assert item.id
            if item.id == endpoint.id:
                has_new_id = True
        assert has_new_id


    def test_get_status(self, client, created_endpoints):
        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)
        status = endpoint.get_status()

        # Check that some fields exist:
        assert "status" in status
        assert "date_created" in status
        assert "id" in status

    def test_direct_update(self, client, created_endpoints, experiment_run, model_for_deployment):
        experiment_run.log_model(model_for_deployment['model'], custom_modules=[])
        experiment_run.log_requirements(['scikit-learn'])

        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        original_status = endpoint.get_status()
        original_build_ids = get_build_ids(original_status)
        updated_status = endpoint.update(experiment_run, DirectUpdateStrategy())

        # Check that a new build is added:
        new_build_ids = get_build_ids(updated_status)
        assert len(new_build_ids) - len(new_build_ids.intersection(original_build_ids)) > 0

    def test_update_wait(self, client, created_endpoints, experiment_run, model_for_deployment):
        experiment_run.log_model(model_for_deployment['model'], custom_modules=[])
        experiment_run.log_requirements(['scikit-learn'])

        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        status = endpoint.update(experiment_run, DirectUpdateStrategy(), True)

        assert status["status"] == "active"

    def test_canary_update(self, client, created_endpoints, experiment_run, model_for_deployment):
        experiment_run.log_model(model_for_deployment['model'], custom_modules=[])
        experiment_run.log_requirements(['scikit-learn'])

        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        original_status = endpoint.get_status()
        original_build_ids = get_build_ids(original_status)

        strategy = CanaryUpdateStrategy(interval=1, step=0.5)

        with pytest.raises(RuntimeError) as excinfo:
            endpoint.update(experiment_run, strategy)

        assert "canary update strategy must have at least one rule" in str(excinfo.value)

        strategy.add_rule(AverageLatencyThresholdRule(0.8))
        updated_status = endpoint.update(experiment_run, strategy)

        # Check that a new build is added:
        new_build_ids = get_build_ids(updated_status)
        assert len(new_build_ids) - len(new_build_ids.intersection(original_build_ids)) > 0

    def test_update_from_json_config(self, client, in_tempdir, created_endpoints, experiment_run, model_for_deployment):
        json = pytest.importorskip("json")
        experiment_run.log_model(model_for_deployment['model'], custom_modules=[])
        experiment_run.log_requirements(['scikit-learn'])

        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        original_status = endpoint.get_status()
        original_build_ids = get_build_ids(original_status)

        # Creating config dict:
        strategy_dict = {
            "run_id": experiment_run.id,
            "strategy": "canary",
            "canary_strategy": {
                "progress_step": 0.05,
                "progress_interval_seconds": 30,
                "rules": [
                    {"rule_id": "1001",
                     "rule_parameters": [
                         {"name": "latency_avg",
                          "value": "0.1"}
                    ]},
                    {"rule_id": "1002",
                     "rule_parameters": [
                        {"name": "error_rate",
                         "value": "1"}
                    ]}
                ]
            }
        }

        filepath = "config.json"
        with open(filepath, "wb") as f:
            json.dump(strategy_dict, f)

        updated_status = endpoint.update_from_config(filepath)
        new_build_ids = get_build_ids(updated_status)
        assert len(new_build_ids) - len(new_build_ids.intersection(original_build_ids)) > 0

    def test_update_from_yaml_config(self, client, in_tempdir, created_endpoints, experiment_run, model_for_deployment):
        yaml = pytest.importorskip("yaml")
        experiment_run.log_model(model_for_deployment['model'], custom_modules=[])
        experiment_run.log_requirements(['scikit-learn'])

        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)

        original_status = endpoint.get_status()
        original_build_ids = get_build_ids(original_status)

        # Creating config dict:
        strategy_dict = {
            "run_id": experiment_run.id,
            "strategy": "canary",
            "canary_strategy": {
                "progress_step": 0.05,
                "progress_interval_seconds": 30,
                "rules": [
                    {"rule_id": "1001",
                     "rule_parameters": [
                         {"name": "latency_avg",
                          "value": "0.1"}
                    ]},
                    {"rule_id": "1002",
                     "rule_parameters": [
                        {"name": "error_rate",
                         "value": "1"}
                    ]}
                ]
            }
        }

        filepath = "config.yaml"
        with open(filepath, "wb") as f:
            yaml.safe_dump(strategy_dict, f)

        updated_status = endpoint.update_from_config(filepath)
        new_build_ids = get_build_ids(updated_status)
        assert len(new_build_ids) - len(new_build_ids.intersection(original_build_ids)) > 0

    def test_get_access_token(self, client, created_endpoints):
        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)
        token = endpoint.get_access_token()

        assert token is None

    def test_get_deployed_model(self, client, experiment_run, model_for_deployment, created_endpoints):
        model = model_for_deployment['model'].fit(
            model_for_deployment['train_features'],
            model_for_deployment['train_targets'],
        )
        experiment_run.log_model(model, custom_modules=[])
        experiment_run.log_requirements(['scikit-learn'])

        path = verta._internal_utils._utils.generate_default_name()
        endpoint = client.set_endpoint(path)
        created_endpoints.append(endpoint)
        endpoint.update(experiment_run, DirectUpdateStrategy())

        while not endpoint.get_status()['status'] == "active":
            time.sleep(3)
        x = model_for_deployment['train_features'].iloc[1].values
        assert endpoint.get_deployed_model().predict([x]) == [2]
