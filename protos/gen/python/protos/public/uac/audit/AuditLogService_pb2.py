# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: uac/audit/AuditLogService.proto

from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from google.api import annotations_pb2 as google_dot_api_dot_annotations__pb2
from google.protobuf import struct_pb2 as google_dot_protobuf_dot_struct__pb2
from ...common import CommonService_pb2 as common_dot_CommonService__pb2
from ...uac import RoleService_pb2 as uac_dot_RoleService__pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='uac/audit/AuditLogService.proto',
  package='ai.verta.uac.versioning',
  syntax='proto3',
  serialized_options=b'P\001Z@github.com/VertaAI/modeldb/protos/gen/go/protos/public/uac/audit',
  serialized_pb=b'\n\x1fuac/audit/AuditLogService.proto\x12\x17\x61i.verta.uac.versioning\x1a\x1cgoogle/api/annotations.proto\x1a\x1cgoogle/protobuf/struct.proto\x1a\x1a\x63ommon/CommonService.proto\x1a\x15uac/RoleService.proto\"\xaa\x01\n\rAuditResource\x12\x13\n\x0bresource_id\x18\x01 \x01(\t\x12\x31\n\rresource_type\x18\x02 \x01(\x0b\x32\x1a.ai.verta.uac.ResourceType\x12;\n\x10resource_service\x18\x03 \x01(\x0e\x32!.ai.verta.uac.ServiceEnum.Service\x12\x14\n\x0cworkspace_id\x18\x04 \x01(\x04\"\x1c\n\tAuditUser\x12\x0f\n\x07user_id\x18\x01 \x01(\t\"\xbd\x02\n\x08\x41uditLog\x12\x30\n\x04user\x18\x01 \x01(\x0b\x32\".ai.verta.uac.versioning.AuditUser\x12\x38\n\x08resource\x18\x02 \x03(\x0b\x32&.ai.verta.uac.versioning.AuditResource\x12$\n\x06\x61\x63tion\x18\x03 \x01(\x0b\x32\x14.ai.verta.uac.Action\x12\x13\n\x0bmethod_name\x18\x08 \x01(\t\x12\x14\n\x0cworkspace_id\x18\t \x01(\x04\x12\x0f\n\x07ts_nano\x18\x04 \x01(\x03\x12\'\n\x07request\x18\x05 \x01(\x0b\x32\x16.google.protobuf.Value\x12(\n\x08response\x18\x06 \x01(\x0b\x32\x16.google.protobuf.Value\x12\x10\n\x08local_id\x18\x07 \x01(\t\"\\\n\x10\x42\x61tchResponseRow\x12\x10\n\x08local_id\x18\x01 \x01(\t\x12\x13\n\x0b\x61\x63knowledge\x18\x02 \x01(\x08\x12\x12\n\nerror_code\x18\x03 \x01(\x05\x12\r\n\x05\x65rror\x18\x04 \x01(\t\"\x90\x01\n\x10\x41\x64\x64\x41uditLogBatch\x12.\n\x03log\x18\x01 \x03(\x0b\x32!.ai.verta.uac.versioning.AuditLog\x1aL\n\x08Response\x12@\n\rresponse_rows\x18\x01 \x03(\x0b\x32).ai.verta.uac.versioning.BatchResponseRow\"9\n\x17RangeTimeStampPredicate\x12\x0f\n\x07\x66rom_ts\x18\x01 \x01(\x03\x12\r\n\x05to_ts\x18\x02 \x01(\x03\"A\n\rUserPredicate\x12\x30\n\x04user\x18\x01 \x01(\x0b\x32\".ai.verta.uac.versioning.AuditUser\"M\n\x11ResourcePredicate\x12\x38\n\x08resource\x18\x01 \x01(\x0b\x32&.ai.verta.uac.versioning.AuditResource\"\x82\x02\n\x12\x41uditLogPredicates\x12M\n\x13timestamp_predicate\x18\x01 \x01(\x0b\x32\x30.ai.verta.uac.versioning.RangeTimeStampPredicate\x12>\n\x0euser_predicate\x18\x02 \x01(\x0b\x32&.ai.verta.uac.versioning.UserPredicate\x12\x46\n\x12resource_predicate\x18\x03 \x01(\x0b\x32*.ai.verta.uac.versioning.ResourcePredicate\x12\x15\n\rworkspace_ids\x18\t \x03(\x04\"\xcf\x01\n\x0c\x46indAuditLog\x12:\n\x05query\x18\x01 \x01(\x0b\x32+.ai.verta.uac.versioning.AuditLogPredicates\x12/\n\npagination\x18\x02 \x01(\x0b\x32\x1b.ai.verta.common.Pagination\x1aR\n\x08Response\x12/\n\x04logs\x18\x01 \x03(\x0b\x32!.ai.verta.uac.versioning.AuditLog\x12\x15\n\rtotal_records\x18\x02 \x01(\x03\x32\xb9\x02\n\x0f\x41uditLogService\x12\x96\x01\n\rpostAuditLogs\x12).ai.verta.uac.versioning.AddAuditLogBatch\x1a\x32.ai.verta.uac.versioning.AddAuditLogBatch.Response\"&\x82\xd3\xe4\x93\x02 \"\x1b/v1/audit-log/postAuditLogs:\x01*\x12\x8c\x01\n\x0c\x66indAuditLog\x12%.ai.verta.uac.versioning.FindAuditLog\x1a..ai.verta.uac.versioning.FindAuditLog.Response\"%\x82\xd3\xe4\x93\x02\x1f\"\x1a/v1/audit-log/findAuditLog:\x01*BDP\x01Z@github.com/VertaAI/modeldb/protos/gen/go/protos/public/uac/auditb\x06proto3'
  ,
  dependencies=[google_dot_api_dot_annotations__pb2.DESCRIPTOR,google_dot_protobuf_dot_struct__pb2.DESCRIPTOR,common_dot_CommonService__pb2.DESCRIPTOR,uac_dot_RoleService__pb2.DESCRIPTOR,])




_AUDITRESOURCE = _descriptor.Descriptor(
  name='AuditResource',
  full_name='ai.verta.uac.versioning.AuditResource',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='resource_id', full_name='ai.verta.uac.versioning.AuditResource.resource_id', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='resource_type', full_name='ai.verta.uac.versioning.AuditResource.resource_type', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='resource_service', full_name='ai.verta.uac.versioning.AuditResource.resource_service', index=2,
      number=3, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='workspace_id', full_name='ai.verta.uac.versioning.AuditResource.workspace_id', index=3,
      number=4, type=4, cpp_type=4, label=1,
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
  serialized_start=172,
  serialized_end=342,
)


_AUDITUSER = _descriptor.Descriptor(
  name='AuditUser',
  full_name='ai.verta.uac.versioning.AuditUser',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='user_id', full_name='ai.verta.uac.versioning.AuditUser.user_id', index=0,
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
  serialized_start=344,
  serialized_end=372,
)


_AUDITLOG = _descriptor.Descriptor(
  name='AuditLog',
  full_name='ai.verta.uac.versioning.AuditLog',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='user', full_name='ai.verta.uac.versioning.AuditLog.user', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='resource', full_name='ai.verta.uac.versioning.AuditLog.resource', index=1,
      number=2, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='action', full_name='ai.verta.uac.versioning.AuditLog.action', index=2,
      number=3, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='method_name', full_name='ai.verta.uac.versioning.AuditLog.method_name', index=3,
      number=8, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='workspace_id', full_name='ai.verta.uac.versioning.AuditLog.workspace_id', index=4,
      number=9, type=4, cpp_type=4, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='ts_nano', full_name='ai.verta.uac.versioning.AuditLog.ts_nano', index=5,
      number=4, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='request', full_name='ai.verta.uac.versioning.AuditLog.request', index=6,
      number=5, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='response', full_name='ai.verta.uac.versioning.AuditLog.response', index=7,
      number=6, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='local_id', full_name='ai.verta.uac.versioning.AuditLog.local_id', index=8,
      number=7, type=9, cpp_type=9, label=1,
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
  serialized_start=375,
  serialized_end=692,
)


_BATCHRESPONSEROW = _descriptor.Descriptor(
  name='BatchResponseRow',
  full_name='ai.verta.uac.versioning.BatchResponseRow',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='local_id', full_name='ai.verta.uac.versioning.BatchResponseRow.local_id', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='acknowledge', full_name='ai.verta.uac.versioning.BatchResponseRow.acknowledge', index=1,
      number=2, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='error_code', full_name='ai.verta.uac.versioning.BatchResponseRow.error_code', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='error', full_name='ai.verta.uac.versioning.BatchResponseRow.error', index=3,
      number=4, type=9, cpp_type=9, label=1,
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
  serialized_start=694,
  serialized_end=786,
)


_ADDAUDITLOGBATCH_RESPONSE = _descriptor.Descriptor(
  name='Response',
  full_name='ai.verta.uac.versioning.AddAuditLogBatch.Response',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='response_rows', full_name='ai.verta.uac.versioning.AddAuditLogBatch.Response.response_rows', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
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
  serialized_start=857,
  serialized_end=933,
)

_ADDAUDITLOGBATCH = _descriptor.Descriptor(
  name='AddAuditLogBatch',
  full_name='ai.verta.uac.versioning.AddAuditLogBatch',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='log', full_name='ai.verta.uac.versioning.AddAuditLogBatch.log', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[_ADDAUDITLOGBATCH_RESPONSE, ],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=789,
  serialized_end=933,
)


_RANGETIMESTAMPPREDICATE = _descriptor.Descriptor(
  name='RangeTimeStampPredicate',
  full_name='ai.verta.uac.versioning.RangeTimeStampPredicate',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='from_ts', full_name='ai.verta.uac.versioning.RangeTimeStampPredicate.from_ts', index=0,
      number=1, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='to_ts', full_name='ai.verta.uac.versioning.RangeTimeStampPredicate.to_ts', index=1,
      number=2, type=3, cpp_type=2, label=1,
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
  serialized_start=935,
  serialized_end=992,
)


_USERPREDICATE = _descriptor.Descriptor(
  name='UserPredicate',
  full_name='ai.verta.uac.versioning.UserPredicate',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='user', full_name='ai.verta.uac.versioning.UserPredicate.user', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
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
  serialized_start=994,
  serialized_end=1059,
)


_RESOURCEPREDICATE = _descriptor.Descriptor(
  name='ResourcePredicate',
  full_name='ai.verta.uac.versioning.ResourcePredicate',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='resource', full_name='ai.verta.uac.versioning.ResourcePredicate.resource', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
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
  serialized_start=1061,
  serialized_end=1138,
)


_AUDITLOGPREDICATES = _descriptor.Descriptor(
  name='AuditLogPredicates',
  full_name='ai.verta.uac.versioning.AuditLogPredicates',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='timestamp_predicate', full_name='ai.verta.uac.versioning.AuditLogPredicates.timestamp_predicate', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='user_predicate', full_name='ai.verta.uac.versioning.AuditLogPredicates.user_predicate', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='resource_predicate', full_name='ai.verta.uac.versioning.AuditLogPredicates.resource_predicate', index=2,
      number=3, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='workspace_ids', full_name='ai.verta.uac.versioning.AuditLogPredicates.workspace_ids', index=3,
      number=9, type=4, cpp_type=4, label=3,
      has_default_value=False, default_value=[],
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
  serialized_start=1141,
  serialized_end=1399,
)


_FINDAUDITLOG_RESPONSE = _descriptor.Descriptor(
  name='Response',
  full_name='ai.verta.uac.versioning.FindAuditLog.Response',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='logs', full_name='ai.verta.uac.versioning.FindAuditLog.Response.logs', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='total_records', full_name='ai.verta.uac.versioning.FindAuditLog.Response.total_records', index=1,
      number=2, type=3, cpp_type=2, label=1,
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
  serialized_start=1527,
  serialized_end=1609,
)

_FINDAUDITLOG = _descriptor.Descriptor(
  name='FindAuditLog',
  full_name='ai.verta.uac.versioning.FindAuditLog',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='query', full_name='ai.verta.uac.versioning.FindAuditLog.query', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='pagination', full_name='ai.verta.uac.versioning.FindAuditLog.pagination', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[_FINDAUDITLOG_RESPONSE, ],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=1402,
  serialized_end=1609,
)

_AUDITRESOURCE.fields_by_name['resource_type'].message_type = uac_dot_RoleService__pb2._RESOURCETYPE
_AUDITRESOURCE.fields_by_name['resource_service'].enum_type = uac_dot_RoleService__pb2._SERVICEENUM_SERVICE
_AUDITLOG.fields_by_name['user'].message_type = _AUDITUSER
_AUDITLOG.fields_by_name['resource'].message_type = _AUDITRESOURCE
_AUDITLOG.fields_by_name['action'].message_type = uac_dot_RoleService__pb2._ACTION
_AUDITLOG.fields_by_name['request'].message_type = google_dot_protobuf_dot_struct__pb2._VALUE
_AUDITLOG.fields_by_name['response'].message_type = google_dot_protobuf_dot_struct__pb2._VALUE
_ADDAUDITLOGBATCH_RESPONSE.fields_by_name['response_rows'].message_type = _BATCHRESPONSEROW
_ADDAUDITLOGBATCH_RESPONSE.containing_type = _ADDAUDITLOGBATCH
_ADDAUDITLOGBATCH.fields_by_name['log'].message_type = _AUDITLOG
_USERPREDICATE.fields_by_name['user'].message_type = _AUDITUSER
_RESOURCEPREDICATE.fields_by_name['resource'].message_type = _AUDITRESOURCE
_AUDITLOGPREDICATES.fields_by_name['timestamp_predicate'].message_type = _RANGETIMESTAMPPREDICATE
_AUDITLOGPREDICATES.fields_by_name['user_predicate'].message_type = _USERPREDICATE
_AUDITLOGPREDICATES.fields_by_name['resource_predicate'].message_type = _RESOURCEPREDICATE
_FINDAUDITLOG_RESPONSE.fields_by_name['logs'].message_type = _AUDITLOG
_FINDAUDITLOG_RESPONSE.containing_type = _FINDAUDITLOG
_FINDAUDITLOG.fields_by_name['query'].message_type = _AUDITLOGPREDICATES
_FINDAUDITLOG.fields_by_name['pagination'].message_type = common_dot_CommonService__pb2._PAGINATION
DESCRIPTOR.message_types_by_name['AuditResource'] = _AUDITRESOURCE
DESCRIPTOR.message_types_by_name['AuditUser'] = _AUDITUSER
DESCRIPTOR.message_types_by_name['AuditLog'] = _AUDITLOG
DESCRIPTOR.message_types_by_name['BatchResponseRow'] = _BATCHRESPONSEROW
DESCRIPTOR.message_types_by_name['AddAuditLogBatch'] = _ADDAUDITLOGBATCH
DESCRIPTOR.message_types_by_name['RangeTimeStampPredicate'] = _RANGETIMESTAMPPREDICATE
DESCRIPTOR.message_types_by_name['UserPredicate'] = _USERPREDICATE
DESCRIPTOR.message_types_by_name['ResourcePredicate'] = _RESOURCEPREDICATE
DESCRIPTOR.message_types_by_name['AuditLogPredicates'] = _AUDITLOGPREDICATES
DESCRIPTOR.message_types_by_name['FindAuditLog'] = _FINDAUDITLOG
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

AuditResource = _reflection.GeneratedProtocolMessageType('AuditResource', (_message.Message,), {
  'DESCRIPTOR' : _AUDITRESOURCE,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.AuditResource)
  })
_sym_db.RegisterMessage(AuditResource)

AuditUser = _reflection.GeneratedProtocolMessageType('AuditUser', (_message.Message,), {
  'DESCRIPTOR' : _AUDITUSER,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.AuditUser)
  })
_sym_db.RegisterMessage(AuditUser)

AuditLog = _reflection.GeneratedProtocolMessageType('AuditLog', (_message.Message,), {
  'DESCRIPTOR' : _AUDITLOG,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.AuditLog)
  })
_sym_db.RegisterMessage(AuditLog)

BatchResponseRow = _reflection.GeneratedProtocolMessageType('BatchResponseRow', (_message.Message,), {
  'DESCRIPTOR' : _BATCHRESPONSEROW,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.BatchResponseRow)
  })
_sym_db.RegisterMessage(BatchResponseRow)

AddAuditLogBatch = _reflection.GeneratedProtocolMessageType('AddAuditLogBatch', (_message.Message,), {

  'Response' : _reflection.GeneratedProtocolMessageType('Response', (_message.Message,), {
    'DESCRIPTOR' : _ADDAUDITLOGBATCH_RESPONSE,
    '__module__' : 'uac.audit.AuditLogService_pb2'
    # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.AddAuditLogBatch.Response)
    })
  ,
  'DESCRIPTOR' : _ADDAUDITLOGBATCH,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.AddAuditLogBatch)
  })
_sym_db.RegisterMessage(AddAuditLogBatch)
_sym_db.RegisterMessage(AddAuditLogBatch.Response)

RangeTimeStampPredicate = _reflection.GeneratedProtocolMessageType('RangeTimeStampPredicate', (_message.Message,), {
  'DESCRIPTOR' : _RANGETIMESTAMPPREDICATE,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.RangeTimeStampPredicate)
  })
_sym_db.RegisterMessage(RangeTimeStampPredicate)

UserPredicate = _reflection.GeneratedProtocolMessageType('UserPredicate', (_message.Message,), {
  'DESCRIPTOR' : _USERPREDICATE,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.UserPredicate)
  })
_sym_db.RegisterMessage(UserPredicate)

ResourcePredicate = _reflection.GeneratedProtocolMessageType('ResourcePredicate', (_message.Message,), {
  'DESCRIPTOR' : _RESOURCEPREDICATE,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.ResourcePredicate)
  })
_sym_db.RegisterMessage(ResourcePredicate)

AuditLogPredicates = _reflection.GeneratedProtocolMessageType('AuditLogPredicates', (_message.Message,), {
  'DESCRIPTOR' : _AUDITLOGPREDICATES,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.AuditLogPredicates)
  })
_sym_db.RegisterMessage(AuditLogPredicates)

FindAuditLog = _reflection.GeneratedProtocolMessageType('FindAuditLog', (_message.Message,), {

  'Response' : _reflection.GeneratedProtocolMessageType('Response', (_message.Message,), {
    'DESCRIPTOR' : _FINDAUDITLOG_RESPONSE,
    '__module__' : 'uac.audit.AuditLogService_pb2'
    # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.FindAuditLog.Response)
    })
  ,
  'DESCRIPTOR' : _FINDAUDITLOG,
  '__module__' : 'uac.audit.AuditLogService_pb2'
  # @@protoc_insertion_point(class_scope:ai.verta.uac.versioning.FindAuditLog)
  })
_sym_db.RegisterMessage(FindAuditLog)
_sym_db.RegisterMessage(FindAuditLog.Response)


DESCRIPTOR._options = None

_AUDITLOGSERVICE = _descriptor.ServiceDescriptor(
  name='AuditLogService',
  full_name='ai.verta.uac.versioning.AuditLogService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=1612,
  serialized_end=1925,
  methods=[
  _descriptor.MethodDescriptor(
    name='postAuditLogs',
    full_name='ai.verta.uac.versioning.AuditLogService.postAuditLogs',
    index=0,
    containing_service=None,
    input_type=_ADDAUDITLOGBATCH,
    output_type=_ADDAUDITLOGBATCH_RESPONSE,
    serialized_options=b'\202\323\344\223\002 \"\033/v1/audit-log/postAuditLogs:\001*',
  ),
  _descriptor.MethodDescriptor(
    name='findAuditLog',
    full_name='ai.verta.uac.versioning.AuditLogService.findAuditLog',
    index=1,
    containing_service=None,
    input_type=_FINDAUDITLOG,
    output_type=_FINDAUDITLOG_RESPONSE,
    serialized_options=b'\202\323\344\223\002\037\"\032/v1/audit-log/findAuditLog:\001*',
  ),
])
_sym_db.RegisterServiceDescriptor(_AUDITLOGSERVICE)

DESCRIPTOR.services_by_name['AuditLogService'] = _AUDITLOGSERVICE

# @@protoc_insertion_point(module_scope)
