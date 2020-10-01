# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: uac/Workspace.proto

from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from google.api import annotations_pb2 as google_dot_api_dot_annotations__pb2
from ..common import CommonService_pb2 as common_dot_CommonService__pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='uac/Workspace.proto',
  package='ai.verta.uac',
  syntax='proto3',
  serialized_options=b'P\001Z:github.com/VertaAI/modeldb/protos/gen/go/protos/public/uac',
  serialized_pb=b'\n\x13uac/Workspace.proto\x12\x0c\x61i.verta.uac\x1a\x1cgoogle/api/annotations.proto\x1a\x1a\x63ommon/CommonService.proto\"\x1e\n\x10GetWorkspaceById\x12\n\n\x02id\x18\x01 \x01(\x04\"\"\n\x12GetWorkspaceByName\x12\x0c\n\x04name\x18\x01 \x01(\t\"n\n\x16GetWorkspaceByLegacyId\x12\n\n\x02id\x18\x01 \x01(\t\x12H\n\x0eworkspace_type\x18\x02 \x01(\x0e\x32\x30.ai.verta.common.WorkspaceTypeEnum.WorkspaceType\"{\n\tWorkspace\x12\n\n\x02id\x18\x01 \x01(\x04\x12\x11\n\x07user_id\x18\x02 \x01(\tH\x00\x12\x10\n\x06org_id\x18\x03 \x01(\tH\x00\x12\x12\n\x08username\x18\x04 \x01(\tH\x01\x12\x12\n\x08org_name\x18\x05 \x01(\tH\x01\x42\r\n\x0binternal_idB\x06\n\x04name2\x8a\x03\n\x10WorkspaceService\x12s\n\x10getWorkspaceById\x12\x1e.ai.verta.uac.GetWorkspaceById\x1a\x17.ai.verta.uac.Workspace\"&\x82\xd3\xe4\x93\x02 \x12\x1e/v1/workspace/getWorkspaceById\x12y\n\x12getWorkspaceByName\x12 .ai.verta.uac.GetWorkspaceByName\x1a\x17.ai.verta.uac.Workspace\"(\x82\xd3\xe4\x93\x02\"\x12 /v1/workspace/getWorkspaceByName\x12\x85\x01\n\x16getWorkspaceByLegacyId\x12$.ai.verta.uac.GetWorkspaceByLegacyId\x1a\x17.ai.verta.uac.Workspace\",\x82\xd3\xe4\x93\x02&\x12$/v1/workspace/getWorkspaceByLegacyIdB>P\x01Z:github.com/VertaAI/modeldb/protos/gen/go/protos/public/uacb\x06proto3'
  ,
  dependencies=[google_dot_api_dot_annotations__pb2.DESCRIPTOR,common_dot_CommonService__pb2.DESCRIPTOR,])




_GETWORKSPACEBYID = _descriptor.Descriptor(
  name='GetWorkspaceById',
  full_name='ai.verta.uac.GetWorkspaceById',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='ai.verta.uac.GetWorkspaceById.id', index=0,
      number=1, type=4, cpp_type=4, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=95,
  serialized_end=125,
)


_GETWORKSPACEBYNAME = _descriptor.Descriptor(
  name='GetWorkspaceByName',
  full_name='ai.verta.uac.GetWorkspaceByName',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='name', full_name='ai.verta.uac.GetWorkspaceByName.name', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=127,
  serialized_end=161,
)


_GETWORKSPACEBYLEGACYID = _descriptor.Descriptor(
  name='GetWorkspaceByLegacyId',
  full_name='ai.verta.uac.GetWorkspaceByLegacyId',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='ai.verta.uac.GetWorkspaceByLegacyId.id', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='workspace_type', full_name='ai.verta.uac.GetWorkspaceByLegacyId.workspace_type', index=1,
      number=2, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=163,
  serialized_end=273,
)


_WORKSPACE = _descriptor.Descriptor(
  name='Workspace',
  full_name='ai.verta.uac.Workspace',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='ai.verta.uac.Workspace.id', index=0,
      number=1, type=4, cpp_type=4, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='user_id', full_name='ai.verta.uac.Workspace.user_id', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='org_id', full_name='ai.verta.uac.Workspace.org_id', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='username', full_name='ai.verta.uac.Workspace.username', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='org_name', full_name='ai.verta.uac.Workspace.org_name', index=4,
      number=5, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
    _descriptor.OneofDescriptor(
      name='internal_id', full_name='ai.verta.uac.Workspace.internal_id',
      index=0, containing_type=None, fields=[]),
    _descriptor.OneofDescriptor(
      name='name', full_name='ai.verta.uac.Workspace.name',
      index=1, containing_type=None, fields=[]),
  ],
  serialized_start=275,
  serialized_end=398,
)

_GETWORKSPACEBYLEGACYID.fields_by_name['workspace_type'].enum_type = common_dot_CommonService__pb2._WORKSPACETYPEENUM_WORKSPACETYPE
_WORKSPACE.oneofs_by_name['internal_id'].fields.append(
  _WORKSPACE.fields_by_name['user_id'])
_WORKSPACE.fields_by_name['user_id'].containing_oneof = _WORKSPACE.oneofs_by_name['internal_id']
_WORKSPACE.oneofs_by_name['internal_id'].fields.append(
  _WORKSPACE.fields_by_name['org_id'])
_WORKSPACE.fields_by_name['org_id'].containing_oneof = _WORKSPACE.oneofs_by_name['internal_id']
_WORKSPACE.oneofs_by_name['name'].fields.append(
  _WORKSPACE.fields_by_name['username'])
_WORKSPACE.fields_by_name['username'].containing_oneof = _WORKSPACE.oneofs_by_name['name']
_WORKSPACE.oneofs_by_name['name'].fields.append(
  _WORKSPACE.fields_by_name['org_name'])
_WORKSPACE.fields_by_name['org_name'].containing_oneof = _WORKSPACE.oneofs_by_name['name']
DESCRIPTOR.message_types_by_name['GetWorkspaceById'] = _GETWORKSPACEBYID
DESCRIPTOR.message_types_by_name['GetWorkspaceByName'] = _GETWORKSPACEBYNAME
DESCRIPTOR.message_types_by_name['GetWorkspaceByLegacyId'] = _GETWORKSPACEBYLEGACYID
DESCRIPTOR.message_types_by_name['Workspace'] = _WORKSPACE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

GetWorkspaceById = _reflection.GeneratedProtocolMessageType('GetWorkspaceById', (_message.Message,), {
  'DESCRIPTOR' : _GETWORKSPACEBYID,
  '__module__' : 'uac.Workspace_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.GetWorkspaceById)
  })
_sym_db.RegisterMessage(GetWorkspaceById)

GetWorkspaceByName = _reflection.GeneratedProtocolMessageType('GetWorkspaceByName', (_message.Message,), {
  'DESCRIPTOR' : _GETWORKSPACEBYNAME,
  '__module__' : 'uac.Workspace_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.GetWorkspaceByName)
  })
_sym_db.RegisterMessage(GetWorkspaceByName)

GetWorkspaceByLegacyId = _reflection.GeneratedProtocolMessageType('GetWorkspaceByLegacyId', (_message.Message,), {
  'DESCRIPTOR' : _GETWORKSPACEBYLEGACYID,
  '__module__' : 'uac.Workspace_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.GetWorkspaceByLegacyId)
  })
_sym_db.RegisterMessage(GetWorkspaceByLegacyId)

Workspace = _reflection.GeneratedProtocolMessageType('Workspace', (_message.Message,), {
  'DESCRIPTOR' : _WORKSPACE,
  '__module__' : 'uac.Workspace_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.Workspace)
  })
_sym_db.RegisterMessage(Workspace)


DESCRIPTOR._options = None

_WORKSPACESERVICE = _descriptor.ServiceDescriptor(
  name='WorkspaceService',
  full_name='ai.verta.uac.WorkspaceService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=401,
  serialized_end=795,
  methods=[
  _descriptor.MethodDescriptor(
    name='getWorkspaceById',
    full_name='ai.verta.uac.WorkspaceService.getWorkspaceById',
    index=0,
    containing_service=None,
    input_type=_GETWORKSPACEBYID,
    output_type=_WORKSPACE,
    serialized_options=b'\202\323\344\223\002 \022\036/v1/workspace/getWorkspaceById',
  ),
  _descriptor.MethodDescriptor(
    name='getWorkspaceByName',
    full_name='ai.verta.uac.WorkspaceService.getWorkspaceByName',
    index=1,
    containing_service=None,
    input_type=_GETWORKSPACEBYNAME,
    output_type=_WORKSPACE,
    serialized_options=b'\202\323\344\223\002\"\022 /v1/workspace/getWorkspaceByName',
  ),
  _descriptor.MethodDescriptor(
    name='getWorkspaceByLegacyId',
    full_name='ai.verta.uac.WorkspaceService.getWorkspaceByLegacyId',
    index=2,
    containing_service=None,
    input_type=_GETWORKSPACEBYLEGACYID,
    output_type=_WORKSPACE,
    serialized_options=b'\202\323\344\223\002&\022$/v1/workspace/getWorkspaceByLegacyId',
  ),
])
_sym_db.RegisterServiceDescriptor(_WORKSPACESERVICE)

DESCRIPTOR.services_by_name['WorkspaceService'] = _WORKSPACESERVICE

# @@protoc_insertion_point(module_scope)
