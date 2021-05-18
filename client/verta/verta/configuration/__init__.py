# -*- coding: utf-8 -*-

from verta._internal_utils import documentation

from ._configuration import _Configuration
from ._hyperparameters import Hyperparameters


documentation.reassign_module(
    [Hyperparameters],
    module_name=__name__,
)
