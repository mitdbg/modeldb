import os

import yaml

import pytest

from verta._internal_utils import _config_utils

from . import utils


@pytest.fixture
def config_filetree(tempdir):
    """
    Creates config files and ``cd``s into a nested directory.

    Yields
    ------
    config : dict
        Expected merged config.

    """
    config_filename = _config_utils.CONFIG_YAML_FILENAME
    config_items = [
        ('email', "hello@verta.ai"),
        ('dev_key', "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX"),
        ('host', "app.verta.ai"),
        ('workspace', "My Workspace"),
        ('project', "My Project"),
        ('experiment', "My Experiment"),
        ('dataset', "My Dataset"),
    ]
    config_iter = iter(config_items)

    # ~/.verta/
    home_dir = os.path.expanduser('~')
    home_verta_dir = os.path.join(home_dir, ".verta", config_filename)
    with open(home_verta_dir, 'w') as f:
        key, value = next(config_iter)
        yaml.safe_dump({key: value}, f)

    try:  # delete home config during teardown
        # 5 parent dirs
        curr_dir = tempdir
        for i in reversed(range(5)):
            curr_dir = os.path.join(curr_dir, "parent{}".format(i+1))
            os.mkdir(curr_dir)
            with open(os.path.join(curr_dir, config_filename), 'w') as f:
                key, value = next(config_iter)
                yaml.safe_dump({key: value}, f)

        # cwd-to-be
        curr_dir = os.path.join(curr_dir, "current")
        os.mkdir(curr_dir)
        with open(os.path.join(curr_dir, config_filename), 'w') as f:
            key, value = next(config_iter)
            yaml.safe_dump({key: value}, f)

        # make sure we've used every config item
        with pytest.raises(StopIteration):
            next(config_iter)

        # children dirs (should not be picked up)
        child_dirs = [
            os.path.join(curr_dir, "childA"),
            os.path.join(curr_dir, "childB"),
        ]
        for child_dir in child_dirs:
            os.mkdir(child_dir)
            with open(os.path.join(child_dir, config_filename), 'w') as f:
                yaml.safe_dump({'INVALID_KEY': "INVALID_VALUE"}, f)

        # cousin dirs (should not be picked up)
        cousin_dirs = [
            os.path.join(curr_dir, "..", "..", "..", "cousinA"),
            os.path.join(curr_dir, "..", "..", "cousinB"),
        ]
        for cousin_dir in cousin_dirs:
            os.mkdir(cousin_dir)
            with open(os.path.join(cousin_dir, config_filename), 'w') as f:
                yaml.safe_dump({'INVALID_KEY': "INVALID_VALUE"}, f)

        with utils.chdir(curr_dir):
            yield dict(config_items)
    finally:
        os.remove(home_verta_dir)


class TestRead:
    def test_merge(self, config_filetree):
        with _config_utils.read_config() as config:
            assert config == config_filetree

    def test_merge_and_overwrite(self):
        pass


class TestWrite:
    @pytest.mark.parametrize("dirpath", ['~', '.'])
    def test_create_empty(self, dirpath):
        config_filepath = _config_utils.create_empty_config_file(dirpath)
        try:
            with open(config_filepath, 'r') as f:
                assert yaml.safe_load(f) == {}
        finally:
            os.remove(config_filepath)

    def test_update_closest(self, config_filetree):
        config_filename = _config_utils.CONFIG_YAML_FILENAME
        new_key = 'NEW_KEY'
        new_value = "NEW_VALUE"

        # config in cwd does not yet have new item
        with open(config_filename, 'r') as f:
            nearest_config = yaml.safe_load(f)
            assert new_key not in nearest_config

        with _config_utils.write_config() as config:
            config.update({new_key: new_value})

        # config in cwd updated with new item
        with open(config_filename, 'r') as f:
            nearest_config = yaml.safe_load(f)
            assert new_key in nearest_config
            assert nearest_config[new_key] == new_value

        # parent config not updated with new item
        with open(os.path.join("..", config_filename), 'r') as f:
            parent_config = yaml.safe_load(f)
            assert new_key not in parent_config
