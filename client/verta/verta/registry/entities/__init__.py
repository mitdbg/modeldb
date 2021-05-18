# -*- coding: utf-8 -*-

from verta._internal_utils import documentation

from ._model import RegisteredModel
from ._models import RegisteredModels
from ._modelversion import RegisteredModelVersion
from ._modelversions import RegisteredModelVersions

documentation.reassign_module(
    [
        RegisteredModel,
        RegisteredModels,
        RegisteredModelVersion,
        RegisteredModelVersions,
    ],
    module_name=__name__,
)
