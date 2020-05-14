# -*- coding: utf-8 -*-

import contextlib
import json
import os

import yaml

from .._protos.public.client import Config_pb2 as _ConfigProtos

from . import _utils


# TODO: make this a named tuple, if it would help readability
CONFIG_YAML_FILENAME = "verta_config.yaml"
CONFIG_JSON_FILENAME = "verta_config.json"
CONFIG_FILENAMES = {
    CONFIG_YAML_FILENAME,
    CONFIG_JSON_FILENAME,
}


@contextlib.contextmanager
def read_config():
    """
    Yields the merged contents of all accessible config files.

    Even though this context does nothing on exit, it's still useful for scopes where
    :func:`write_config` is also being used, to help make sure the contents aren't being mixed up.

    Yields
    ------
    config : dict
        Merged contents of all accessible config files.

    """
    config = {}
    for filepath in reversed(find_config_files()):
        merge(config, load(filepath))

    yield config


@contextlib.contextmanager
def write_config():
    """
    Updates the nearest config file.

    Yields
    ------
    config : dict
        Contents of the nearest config file.

    """
    config_filepath = find_closest_config_file()

    config = load(config_filepath)

    yield config

    dump(config, config_filepath)


def load(config_filepath):
    config_filepath = os.path.expanduser(config_filepath)

    with open(config_filepath, 'r') as f:
        if config_filepath.endswith('.yaml'):
            config = yaml.safe_load(f)
        else:  # JSON
            config = json.load(f)

    validate(config)

    return config


def dump(config, config_filepath):
    config_filepath = os.path.expanduser(config_filepath)

    with open(config_filepath, 'w') as f:
        if config_filepath.endswith('.yaml'):
            yaml.safe_dump(config, f)
        else:  # JSON
            json.dump(config, f)


def create_empty_config_file(dirpath):
    """
    Creates ``verta_config.yaml`` containing an empty dictionary in `dirpath`.

    Parameters
    ----------
    dirpath : str
        Path to the directory that will contain the config file.

    Returns
    -------
    config_filepath : str
        Absolute path to the newly-created config file

    """
    config_filepath = os.path.join(dirpath, CONFIG_YAML_FILENAME)
    config_filepath = os.path.expanduser(config_filepath)
    config_filepath = os.path.abspath(config_filepath)

    with open(config_filepath, 'w') as f:
        yaml.dump({}, f)

    return config_filepath


def get_possible_config_file_dirs():
    """
    Returns the directories where config files could be found.

    Config files may be found in the following locations, in order:

    * current directory
    * parent directories until the root
    * ``$HOME/.verta/``

    Returns
    -------
    dirpaths : list of str
        Directories that could contain config files, with the closest to the current directory
        being first.

    """
    dirpaths = []
    cur_dir = os.getcwd()
    while (not dirpaths  # first iteration (dirpaths empty)
           or cur_dir != dirpaths[-1]):
        dirpaths.append(cur_dir)
        cur_dir = os.path.dirname(cur_dir)
    dirpaths.append(os.path.expanduser("~/.verta"))

    return dirpaths


def find_closest_config_file():
    """
    Returns the location of the closest Verta config file.

    Returns
    -------
    config_filepath: str or None
        Path to config file.

    """
    for dirpath in get_possible_config_file_dirs():
        # TODO: raise error if YAML and JSON in same dir
        filepaths = CONFIG_FILENAMES.intersection(os.listdir(dirpath))
        if filepaths:
            return os.path.join(dirpath, filepaths.pop())
    else:
        return None

def find_config_files():
    """
    Returns the locations of accessible Verta config files.

    Returns
    -------
    config_filepaths : list of str
        Paths to config files, with the closest to the current directory being first.

    """
    filepaths = []
    for dirpath in get_possible_config_file_dirs():
        # TODO: raise error if YAML and JSON in same dir
        filepaths.extend(
            os.path.join(dirpath, config_filename)
            for config_filename
            in CONFIG_FILENAMES.intersection(os.listdir(dirpath))
        )

    return filepaths


def validate(config):
    """Validates `config` against the protobuf spec."""
    _utils.json_to_proto(
        config, _ConfigProtos.Config,
        ignore_unknown_fields=True,  # TODO: reset to False when protos are impl
    )


def merge(accum, other):
    """
    Merges `other` into `accum` in place.

    A ``dict`` at the same location will be updated. A ``list`` at the same location will be
    appended to. A scalar at the same location will be overwritten.

    Parameters
    ----------
    accum : dict
        Config (or field, if being called recursively) being accumulated.
    other : dict
        Incoming config (or field, if being called recursively).

    Warnings
    --------
    This function will encounter bugs if values at the same location are of different types, so it
    should only be called after the configs have been validated against the protobuf spec.

    Notes
    -----
    Adapted from https://stackoverflow.com/a/20666342/8651995.

    """
    for key, value in other.items():
        if isinstance(value, dict):
            node = accum.setdefault(key, {})
            merge(node, value)
        elif isinstance(value, list):
            node = accum.set_default(key, [])
            node.extend(value)
        else:
            accum[key] = value
