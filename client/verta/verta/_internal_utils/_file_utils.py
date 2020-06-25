# -*- coding: utf-8 -*-

import os


# TODO: migrate file utils from _utils and _artifact_utils


def increment_path(path):
    """
    Adds or increments a number near the end of `path` to support avoiding collisions.

    .. note::

        If `path` has multiple extensions , the number will be placed before the final one (see
        **Examples**). This is consistent with how the Python ``wget`` library avoids collisions
        and how macOS names file copies.

    Parameters
    ----------
    path : str
        File or directory path.

    Returns
    -------
    new_path : str
        `path` with an added or incremented number.

    Examples
    --------
    .. code-block:: python

        increment_path("data.csv")
        # data 1.csv
        increment_path("data 1.csv")
        # data 2.csv
        increment_path("archive.tar.gz")
        # archive.tar 1.gz

    """
    base, ext = os.path.splitext(path)

    # check if name already has number
    if ' ' in base:
        original_base, number_str = base.rsplit(' ', 1)
        if number_str.isdigit():
            # increment number
            number = int(number_str) + 1
            return original_base + " {}".format(number) + ext

    # add number
    return base + " 1" + ext


def remove_prefix_dir(path, prefix_dir):
    """
    Removes `prefix_dir` from the beginning of `path`.

    Returns `path` unaltered if it does not start with `prefix_dir`

    Parameters
    ----------
    path : str
        File or directory path.
    prefix_dir : str
        Directory path to remove from the beginning of `path`.

    Returns
    -------
    new_path : str
        `path` without `prefix_dir` at its beginning.

    """
    # append a slash so we avoid matching non-directory prefixes
    #     For example, this shouldn't match:
    #         path       = "data/census-train.csv"
    #         prefix_dir = "data/census"
    #     But this should:
    #         path       = "data/census/train.csv"
    #         prefix_dir = "data/census"
    if not prefix_dir.endswith('/'):
        prefix_dir += '/'

    if path.startswith(prefix_dir):
        path = path[len(prefix_dir):]
        path = path.lstrip('/')  # remove leading slashes, e.g. for "s3:"

    return path
