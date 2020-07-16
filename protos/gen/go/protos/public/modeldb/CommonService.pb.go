// Code generated by protoc-gen-go. DO NOT EDIT.
// source: modeldb/CommonService.proto

package modeldb

import (
	fmt "fmt"
	common "github.com/VertaAI/modeldb/protos/gen/go/protos/public/common"
	proto "github.com/golang/protobuf/proto"
	_ "github.com/golang/protobuf/ptypes/struct"
	math "math"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.ProtoPackageIsVersion3 // please upgrade the proto package

type Feature struct {
	Name                 string   `protobuf:"bytes,1,opt,name=name,proto3" json:"name,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Feature) Reset()         { *m = Feature{} }
func (m *Feature) String() string { return proto.CompactTextString(m) }
func (*Feature) ProtoMessage()    {}
func (*Feature) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{0}
}

func (m *Feature) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Feature.Unmarshal(m, b)
}
func (m *Feature) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Feature.Marshal(b, m, deterministic)
}
func (m *Feature) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Feature.Merge(m, src)
}
func (m *Feature) XXX_Size() int {
	return xxx_messageInfo_Feature.Size(m)
}
func (m *Feature) XXX_DiscardUnknown() {
	xxx_messageInfo_Feature.DiscardUnknown(m)
}

var xxx_messageInfo_Feature proto.InternalMessageInfo

func (m *Feature) GetName() string {
	if m != nil {
		return m.Name
	}
	return ""
}

// attributes
type GetAttributes struct {
	Id                   string   `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	AttributeKeys        []string `protobuf:"bytes,2,rep,name=attribute_keys,json=attributeKeys,proto3" json:"attribute_keys,omitempty"`
	GetAll               bool     `protobuf:"varint,3,opt,name=get_all,json=getAll,proto3" json:"get_all,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *GetAttributes) Reset()         { *m = GetAttributes{} }
func (m *GetAttributes) String() string { return proto.CompactTextString(m) }
func (*GetAttributes) ProtoMessage()    {}
func (*GetAttributes) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{1}
}

func (m *GetAttributes) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetAttributes.Unmarshal(m, b)
}
func (m *GetAttributes) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetAttributes.Marshal(b, m, deterministic)
}
func (m *GetAttributes) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetAttributes.Merge(m, src)
}
func (m *GetAttributes) XXX_Size() int {
	return xxx_messageInfo_GetAttributes.Size(m)
}
func (m *GetAttributes) XXX_DiscardUnknown() {
	xxx_messageInfo_GetAttributes.DiscardUnknown(m)
}

var xxx_messageInfo_GetAttributes proto.InternalMessageInfo

func (m *GetAttributes) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *GetAttributes) GetAttributeKeys() []string {
	if m != nil {
		return m.AttributeKeys
	}
	return nil
}

func (m *GetAttributes) GetGetAll() bool {
	if m != nil {
		return m.GetAll
	}
	return false
}

type GetAttributes_Response struct {
	Attributes           []*common.KeyValue `protobuf:"bytes,1,rep,name=attributes,proto3" json:"attributes,omitempty"`
	XXX_NoUnkeyedLiteral struct{}           `json:"-"`
	XXX_unrecognized     []byte             `json:"-"`
	XXX_sizecache        int32              `json:"-"`
}

func (m *GetAttributes_Response) Reset()         { *m = GetAttributes_Response{} }
func (m *GetAttributes_Response) String() string { return proto.CompactTextString(m) }
func (*GetAttributes_Response) ProtoMessage()    {}
func (*GetAttributes_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{1, 0}
}

func (m *GetAttributes_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetAttributes_Response.Unmarshal(m, b)
}
func (m *GetAttributes_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetAttributes_Response.Marshal(b, m, deterministic)
}
func (m *GetAttributes_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetAttributes_Response.Merge(m, src)
}
func (m *GetAttributes_Response) XXX_Size() int {
	return xxx_messageInfo_GetAttributes_Response.Size(m)
}
func (m *GetAttributes_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_GetAttributes_Response.DiscardUnknown(m)
}

var xxx_messageInfo_GetAttributes_Response proto.InternalMessageInfo

func (m *GetAttributes_Response) GetAttributes() []*common.KeyValue {
	if m != nil {
		return m.Attributes
	}
	return nil
}

// TODO: make this update attribute
type AddAttributes struct {
	Id                   string           `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Attribute            *common.KeyValue `protobuf:"bytes,2,opt,name=attribute,proto3" json:"attribute,omitempty"`
	XXX_NoUnkeyedLiteral struct{}         `json:"-"`
	XXX_unrecognized     []byte           `json:"-"`
	XXX_sizecache        int32            `json:"-"`
}

func (m *AddAttributes) Reset()         { *m = AddAttributes{} }
func (m *AddAttributes) String() string { return proto.CompactTextString(m) }
func (*AddAttributes) ProtoMessage()    {}
func (*AddAttributes) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{2}
}

func (m *AddAttributes) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_AddAttributes.Unmarshal(m, b)
}
func (m *AddAttributes) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_AddAttributes.Marshal(b, m, deterministic)
}
func (m *AddAttributes) XXX_Merge(src proto.Message) {
	xxx_messageInfo_AddAttributes.Merge(m, src)
}
func (m *AddAttributes) XXX_Size() int {
	return xxx_messageInfo_AddAttributes.Size(m)
}
func (m *AddAttributes) XXX_DiscardUnknown() {
	xxx_messageInfo_AddAttributes.DiscardUnknown(m)
}

var xxx_messageInfo_AddAttributes proto.InternalMessageInfo

func (m *AddAttributes) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *AddAttributes) GetAttribute() *common.KeyValue {
	if m != nil {
		return m.Attribute
	}
	return nil
}

type AddAttributes_Response struct {
	Status               bool     `protobuf:"varint,1,opt,name=status,proto3" json:"status,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *AddAttributes_Response) Reset()         { *m = AddAttributes_Response{} }
func (m *AddAttributes_Response) String() string { return proto.CompactTextString(m) }
func (*AddAttributes_Response) ProtoMessage()    {}
func (*AddAttributes_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{2, 0}
}

func (m *AddAttributes_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_AddAttributes_Response.Unmarshal(m, b)
}
func (m *AddAttributes_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_AddAttributes_Response.Marshal(b, m, deterministic)
}
func (m *AddAttributes_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_AddAttributes_Response.Merge(m, src)
}
func (m *AddAttributes_Response) XXX_Size() int {
	return xxx_messageInfo_AddAttributes_Response.Size(m)
}
func (m *AddAttributes_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_AddAttributes_Response.DiscardUnknown(m)
}

var xxx_messageInfo_AddAttributes_Response proto.InternalMessageInfo

func (m *AddAttributes_Response) GetStatus() bool {
	if m != nil {
		return m.Status
	}
	return false
}

type GetTags struct {
	Id                   string   `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *GetTags) Reset()         { *m = GetTags{} }
func (m *GetTags) String() string { return proto.CompactTextString(m) }
func (*GetTags) ProtoMessage()    {}
func (*GetTags) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{3}
}

func (m *GetTags) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetTags.Unmarshal(m, b)
}
func (m *GetTags) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetTags.Marshal(b, m, deterministic)
}
func (m *GetTags) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetTags.Merge(m, src)
}
func (m *GetTags) XXX_Size() int {
	return xxx_messageInfo_GetTags.Size(m)
}
func (m *GetTags) XXX_DiscardUnknown() {
	xxx_messageInfo_GetTags.DiscardUnknown(m)
}

var xxx_messageInfo_GetTags proto.InternalMessageInfo

func (m *GetTags) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

type GetTags_Response struct {
	Tags                 []string `protobuf:"bytes,1,rep,name=tags,proto3" json:"tags,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *GetTags_Response) Reset()         { *m = GetTags_Response{} }
func (m *GetTags_Response) String() string { return proto.CompactTextString(m) }
func (*GetTags_Response) ProtoMessage()    {}
func (*GetTags_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{3, 0}
}

func (m *GetTags_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetTags_Response.Unmarshal(m, b)
}
func (m *GetTags_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetTags_Response.Marshal(b, m, deterministic)
}
func (m *GetTags_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetTags_Response.Merge(m, src)
}
func (m *GetTags_Response) XXX_Size() int {
	return xxx_messageInfo_GetTags_Response.Size(m)
}
func (m *GetTags_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_GetTags_Response.DiscardUnknown(m)
}

var xxx_messageInfo_GetTags_Response proto.InternalMessageInfo

func (m *GetTags_Response) GetTags() []string {
	if m != nil {
		return m.Tags
	}
	return nil
}

// code version
type CodeVersion struct {
	// Types that are valid to be assigned to Code:
	//	*CodeVersion_GitSnapshot
	//	*CodeVersion_CodeArchive
	Code                 isCodeVersion_Code `protobuf_oneof:"code"`
	DateLogged           uint64             `protobuf:"varint,3,opt,name=date_logged,json=dateLogged,proto3" json:"date_logged,omitempty"`
	XXX_NoUnkeyedLiteral struct{}           `json:"-"`
	XXX_unrecognized     []byte             `json:"-"`
	XXX_sizecache        int32              `json:"-"`
}

func (m *CodeVersion) Reset()         { *m = CodeVersion{} }
func (m *CodeVersion) String() string { return proto.CompactTextString(m) }
func (*CodeVersion) ProtoMessage()    {}
func (*CodeVersion) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{4}
}

func (m *CodeVersion) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_CodeVersion.Unmarshal(m, b)
}
func (m *CodeVersion) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_CodeVersion.Marshal(b, m, deterministic)
}
func (m *CodeVersion) XXX_Merge(src proto.Message) {
	xxx_messageInfo_CodeVersion.Merge(m, src)
}
func (m *CodeVersion) XXX_Size() int {
	return xxx_messageInfo_CodeVersion.Size(m)
}
func (m *CodeVersion) XXX_DiscardUnknown() {
	xxx_messageInfo_CodeVersion.DiscardUnknown(m)
}

var xxx_messageInfo_CodeVersion proto.InternalMessageInfo

type isCodeVersion_Code interface {
	isCodeVersion_Code()
}

type CodeVersion_GitSnapshot struct {
	GitSnapshot *GitSnapshot `protobuf:"bytes,1,opt,name=git_snapshot,json=gitSnapshot,proto3,oneof"`
}

type CodeVersion_CodeArchive struct {
	CodeArchive *common.Artifact `protobuf:"bytes,2,opt,name=code_archive,json=codeArchive,proto3,oneof"`
}

func (*CodeVersion_GitSnapshot) isCodeVersion_Code() {}

func (*CodeVersion_CodeArchive) isCodeVersion_Code() {}

func (m *CodeVersion) GetCode() isCodeVersion_Code {
	if m != nil {
		return m.Code
	}
	return nil
}

func (m *CodeVersion) GetGitSnapshot() *GitSnapshot {
	if x, ok := m.GetCode().(*CodeVersion_GitSnapshot); ok {
		return x.GitSnapshot
	}
	return nil
}

func (m *CodeVersion) GetCodeArchive() *common.Artifact {
	if x, ok := m.GetCode().(*CodeVersion_CodeArchive); ok {
		return x.CodeArchive
	}
	return nil
}

func (m *CodeVersion) GetDateLogged() uint64 {
	if m != nil {
		return m.DateLogged
	}
	return 0
}

// XXX_OneofWrappers is for the internal use of the proto package.
func (*CodeVersion) XXX_OneofWrappers() []interface{} {
	return []interface{}{
		(*CodeVersion_GitSnapshot)(nil),
		(*CodeVersion_CodeArchive)(nil),
	}
}

type GitSnapshot struct {
	Filepaths            []string                   `protobuf:"bytes,1,rep,name=filepaths,proto3" json:"filepaths,omitempty"`
	Repo                 string                     `protobuf:"bytes,2,opt,name=repo,proto3" json:"repo,omitempty"`
	Hash                 string                     `protobuf:"bytes,3,opt,name=hash,proto3" json:"hash,omitempty"`
	IsDirty              common.TernaryEnum_Ternary `protobuf:"varint,4,opt,name=is_dirty,json=isDirty,proto3,enum=ai.verta.common.TernaryEnum_Ternary" json:"is_dirty,omitempty"`
	XXX_NoUnkeyedLiteral struct{}                   `json:"-"`
	XXX_unrecognized     []byte                     `json:"-"`
	XXX_sizecache        int32                      `json:"-"`
}

func (m *GitSnapshot) Reset()         { *m = GitSnapshot{} }
func (m *GitSnapshot) String() string { return proto.CompactTextString(m) }
func (*GitSnapshot) ProtoMessage()    {}
func (*GitSnapshot) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{5}
}

func (m *GitSnapshot) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GitSnapshot.Unmarshal(m, b)
}
func (m *GitSnapshot) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GitSnapshot.Marshal(b, m, deterministic)
}
func (m *GitSnapshot) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GitSnapshot.Merge(m, src)
}
func (m *GitSnapshot) XXX_Size() int {
	return xxx_messageInfo_GitSnapshot.Size(m)
}
func (m *GitSnapshot) XXX_DiscardUnknown() {
	xxx_messageInfo_GitSnapshot.DiscardUnknown(m)
}

var xxx_messageInfo_GitSnapshot proto.InternalMessageInfo

func (m *GitSnapshot) GetFilepaths() []string {
	if m != nil {
		return m.Filepaths
	}
	return nil
}

func (m *GitSnapshot) GetRepo() string {
	if m != nil {
		return m.Repo
	}
	return ""
}

func (m *GitSnapshot) GetHash() string {
	if m != nil {
		return m.Hash
	}
	return ""
}

func (m *GitSnapshot) GetIsDirty() common.TernaryEnum_Ternary {
	if m != nil {
		return m.IsDirty
	}
	return common.TernaryEnum_UNKNOWN
}

type GetUrlForArtifact struct {
	Id                   string                               `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Key                  string                               `protobuf:"bytes,2,opt,name=key,proto3" json:"key,omitempty"`
	Method               string                               `protobuf:"bytes,3,opt,name=method,proto3" json:"method,omitempty"`
	ArtifactType         common.ArtifactTypeEnum_ArtifactType `protobuf:"varint,4,opt,name=artifact_type,json=artifactType,proto3,enum=ai.verta.common.ArtifactTypeEnum_ArtifactType" json:"artifact_type,omitempty"`
	PartNumber           uint64                               `protobuf:"varint,5,opt,name=part_number,json=partNumber,proto3" json:"part_number,omitempty"`
	XXX_NoUnkeyedLiteral struct{}                             `json:"-"`
	XXX_unrecognized     []byte                               `json:"-"`
	XXX_sizecache        int32                                `json:"-"`
}

func (m *GetUrlForArtifact) Reset()         { *m = GetUrlForArtifact{} }
func (m *GetUrlForArtifact) String() string { return proto.CompactTextString(m) }
func (*GetUrlForArtifact) ProtoMessage()    {}
func (*GetUrlForArtifact) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{6}
}

func (m *GetUrlForArtifact) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetUrlForArtifact.Unmarshal(m, b)
}
func (m *GetUrlForArtifact) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetUrlForArtifact.Marshal(b, m, deterministic)
}
func (m *GetUrlForArtifact) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetUrlForArtifact.Merge(m, src)
}
func (m *GetUrlForArtifact) XXX_Size() int {
	return xxx_messageInfo_GetUrlForArtifact.Size(m)
}
func (m *GetUrlForArtifact) XXX_DiscardUnknown() {
	xxx_messageInfo_GetUrlForArtifact.DiscardUnknown(m)
}

var xxx_messageInfo_GetUrlForArtifact proto.InternalMessageInfo

func (m *GetUrlForArtifact) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *GetUrlForArtifact) GetKey() string {
	if m != nil {
		return m.Key
	}
	return ""
}

func (m *GetUrlForArtifact) GetMethod() string {
	if m != nil {
		return m.Method
	}
	return ""
}

func (m *GetUrlForArtifact) GetArtifactType() common.ArtifactTypeEnum_ArtifactType {
	if m != nil {
		return m.ArtifactType
	}
	return common.ArtifactTypeEnum_IMAGE
}

func (m *GetUrlForArtifact) GetPartNumber() uint64 {
	if m != nil {
		return m.PartNumber
	}
	return 0
}

type GetUrlForArtifact_Response struct {
	Url                  string            `protobuf:"bytes,1,opt,name=url,proto3" json:"url,omitempty"`
	Fields               map[string]string `protobuf:"bytes,2,rep,name=fields,proto3" json:"fields,omitempty" protobuf_key:"bytes,1,opt,name=key,proto3" protobuf_val:"bytes,2,opt,name=value,proto3"`
	MultipartUploadOk    bool              `protobuf:"varint,3,opt,name=multipart_upload_ok,json=multipartUploadOk,proto3" json:"multipart_upload_ok,omitempty"`
	XXX_NoUnkeyedLiteral struct{}          `json:"-"`
	XXX_unrecognized     []byte            `json:"-"`
	XXX_sizecache        int32             `json:"-"`
}

func (m *GetUrlForArtifact_Response) Reset()         { *m = GetUrlForArtifact_Response{} }
func (m *GetUrlForArtifact_Response) String() string { return proto.CompactTextString(m) }
func (*GetUrlForArtifact_Response) ProtoMessage()    {}
func (*GetUrlForArtifact_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{6, 0}
}

func (m *GetUrlForArtifact_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetUrlForArtifact_Response.Unmarshal(m, b)
}
func (m *GetUrlForArtifact_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetUrlForArtifact_Response.Marshal(b, m, deterministic)
}
func (m *GetUrlForArtifact_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetUrlForArtifact_Response.Merge(m, src)
}
func (m *GetUrlForArtifact_Response) XXX_Size() int {
	return xxx_messageInfo_GetUrlForArtifact_Response.Size(m)
}
func (m *GetUrlForArtifact_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_GetUrlForArtifact_Response.DiscardUnknown(m)
}

var xxx_messageInfo_GetUrlForArtifact_Response proto.InternalMessageInfo

func (m *GetUrlForArtifact_Response) GetUrl() string {
	if m != nil {
		return m.Url
	}
	return ""
}

func (m *GetUrlForArtifact_Response) GetFields() map[string]string {
	if m != nil {
		return m.Fields
	}
	return nil
}

func (m *GetUrlForArtifact_Response) GetMultipartUploadOk() bool {
	if m != nil {
		return m.MultipartUploadOk
	}
	return false
}

type CommitArtifactPart struct {
	Id                   string               `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Key                  string               `protobuf:"bytes,2,opt,name=key,proto3" json:"key,omitempty"`
	ArtifactPart         *common.ArtifactPart `protobuf:"bytes,3,opt,name=artifact_part,json=artifactPart,proto3" json:"artifact_part,omitempty"`
	XXX_NoUnkeyedLiteral struct{}             `json:"-"`
	XXX_unrecognized     []byte               `json:"-"`
	XXX_sizecache        int32                `json:"-"`
}

func (m *CommitArtifactPart) Reset()         { *m = CommitArtifactPart{} }
func (m *CommitArtifactPart) String() string { return proto.CompactTextString(m) }
func (*CommitArtifactPart) ProtoMessage()    {}
func (*CommitArtifactPart) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{7}
}

func (m *CommitArtifactPart) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_CommitArtifactPart.Unmarshal(m, b)
}
func (m *CommitArtifactPart) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_CommitArtifactPart.Marshal(b, m, deterministic)
}
func (m *CommitArtifactPart) XXX_Merge(src proto.Message) {
	xxx_messageInfo_CommitArtifactPart.Merge(m, src)
}
func (m *CommitArtifactPart) XXX_Size() int {
	return xxx_messageInfo_CommitArtifactPart.Size(m)
}
func (m *CommitArtifactPart) XXX_DiscardUnknown() {
	xxx_messageInfo_CommitArtifactPart.DiscardUnknown(m)
}

var xxx_messageInfo_CommitArtifactPart proto.InternalMessageInfo

func (m *CommitArtifactPart) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *CommitArtifactPart) GetKey() string {
	if m != nil {
		return m.Key
	}
	return ""
}

func (m *CommitArtifactPart) GetArtifactPart() *common.ArtifactPart {
	if m != nil {
		return m.ArtifactPart
	}
	return nil
}

type CommitArtifactPart_Response struct {
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *CommitArtifactPart_Response) Reset()         { *m = CommitArtifactPart_Response{} }
func (m *CommitArtifactPart_Response) String() string { return proto.CompactTextString(m) }
func (*CommitArtifactPart_Response) ProtoMessage()    {}
func (*CommitArtifactPart_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{7, 0}
}

func (m *CommitArtifactPart_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_CommitArtifactPart_Response.Unmarshal(m, b)
}
func (m *CommitArtifactPart_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_CommitArtifactPart_Response.Marshal(b, m, deterministic)
}
func (m *CommitArtifactPart_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_CommitArtifactPart_Response.Merge(m, src)
}
func (m *CommitArtifactPart_Response) XXX_Size() int {
	return xxx_messageInfo_CommitArtifactPart_Response.Size(m)
}
func (m *CommitArtifactPart_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_CommitArtifactPart_Response.DiscardUnknown(m)
}

var xxx_messageInfo_CommitArtifactPart_Response proto.InternalMessageInfo

type GetCommittedArtifactParts struct {
	Id                   string   `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Key                  string   `protobuf:"bytes,2,opt,name=key,proto3" json:"key,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *GetCommittedArtifactParts) Reset()         { *m = GetCommittedArtifactParts{} }
func (m *GetCommittedArtifactParts) String() string { return proto.CompactTextString(m) }
func (*GetCommittedArtifactParts) ProtoMessage()    {}
func (*GetCommittedArtifactParts) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{8}
}

func (m *GetCommittedArtifactParts) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetCommittedArtifactParts.Unmarshal(m, b)
}
func (m *GetCommittedArtifactParts) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetCommittedArtifactParts.Marshal(b, m, deterministic)
}
func (m *GetCommittedArtifactParts) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetCommittedArtifactParts.Merge(m, src)
}
func (m *GetCommittedArtifactParts) XXX_Size() int {
	return xxx_messageInfo_GetCommittedArtifactParts.Size(m)
}
func (m *GetCommittedArtifactParts) XXX_DiscardUnknown() {
	xxx_messageInfo_GetCommittedArtifactParts.DiscardUnknown(m)
}

var xxx_messageInfo_GetCommittedArtifactParts proto.InternalMessageInfo

func (m *GetCommittedArtifactParts) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *GetCommittedArtifactParts) GetKey() string {
	if m != nil {
		return m.Key
	}
	return ""
}

type GetCommittedArtifactParts_Response struct {
	ArtifactParts        []*common.ArtifactPart `protobuf:"bytes,1,rep,name=artifact_parts,json=artifactParts,proto3" json:"artifact_parts,omitempty"`
	XXX_NoUnkeyedLiteral struct{}               `json:"-"`
	XXX_unrecognized     []byte                 `json:"-"`
	XXX_sizecache        int32                  `json:"-"`
}

func (m *GetCommittedArtifactParts_Response) Reset()         { *m = GetCommittedArtifactParts_Response{} }
func (m *GetCommittedArtifactParts_Response) String() string { return proto.CompactTextString(m) }
func (*GetCommittedArtifactParts_Response) ProtoMessage()    {}
func (*GetCommittedArtifactParts_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{8, 0}
}

func (m *GetCommittedArtifactParts_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetCommittedArtifactParts_Response.Unmarshal(m, b)
}
func (m *GetCommittedArtifactParts_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetCommittedArtifactParts_Response.Marshal(b, m, deterministic)
}
func (m *GetCommittedArtifactParts_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetCommittedArtifactParts_Response.Merge(m, src)
}
func (m *GetCommittedArtifactParts_Response) XXX_Size() int {
	return xxx_messageInfo_GetCommittedArtifactParts_Response.Size(m)
}
func (m *GetCommittedArtifactParts_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_GetCommittedArtifactParts_Response.DiscardUnknown(m)
}

var xxx_messageInfo_GetCommittedArtifactParts_Response proto.InternalMessageInfo

func (m *GetCommittedArtifactParts_Response) GetArtifactParts() []*common.ArtifactPart {
	if m != nil {
		return m.ArtifactParts
	}
	return nil
}

type CommitMultipartArtifact struct {
	Id                   string   `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Key                  string   `protobuf:"bytes,2,opt,name=key,proto3" json:"key,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *CommitMultipartArtifact) Reset()         { *m = CommitMultipartArtifact{} }
func (m *CommitMultipartArtifact) String() string { return proto.CompactTextString(m) }
func (*CommitMultipartArtifact) ProtoMessage()    {}
func (*CommitMultipartArtifact) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{9}
}

func (m *CommitMultipartArtifact) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_CommitMultipartArtifact.Unmarshal(m, b)
}
func (m *CommitMultipartArtifact) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_CommitMultipartArtifact.Marshal(b, m, deterministic)
}
func (m *CommitMultipartArtifact) XXX_Merge(src proto.Message) {
	xxx_messageInfo_CommitMultipartArtifact.Merge(m, src)
}
func (m *CommitMultipartArtifact) XXX_Size() int {
	return xxx_messageInfo_CommitMultipartArtifact.Size(m)
}
func (m *CommitMultipartArtifact) XXX_DiscardUnknown() {
	xxx_messageInfo_CommitMultipartArtifact.DiscardUnknown(m)
}

var xxx_messageInfo_CommitMultipartArtifact proto.InternalMessageInfo

func (m *CommitMultipartArtifact) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *CommitMultipartArtifact) GetKey() string {
	if m != nil {
		return m.Key
	}
	return ""
}

type CommitMultipartArtifact_Response struct {
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *CommitMultipartArtifact_Response) Reset()         { *m = CommitMultipartArtifact_Response{} }
func (m *CommitMultipartArtifact_Response) String() string { return proto.CompactTextString(m) }
func (*CommitMultipartArtifact_Response) ProtoMessage()    {}
func (*CommitMultipartArtifact_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{9, 0}
}

func (m *CommitMultipartArtifact_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_CommitMultipartArtifact_Response.Unmarshal(m, b)
}
func (m *CommitMultipartArtifact_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_CommitMultipartArtifact_Response.Marshal(b, m, deterministic)
}
func (m *CommitMultipartArtifact_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_CommitMultipartArtifact_Response.Merge(m, src)
}
func (m *CommitMultipartArtifact_Response) XXX_Size() int {
	return xxx_messageInfo_CommitMultipartArtifact_Response.Size(m)
}
func (m *CommitMultipartArtifact_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_CommitMultipartArtifact_Response.DiscardUnknown(m)
}

var xxx_messageInfo_CommitMultipartArtifact_Response proto.InternalMessageInfo

// TODO: add bulk and get_all
type GetArtifacts struct {
	Id                   string   `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Key                  string   `protobuf:"bytes,2,opt,name=key,proto3" json:"key,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *GetArtifacts) Reset()         { *m = GetArtifacts{} }
func (m *GetArtifacts) String() string { return proto.CompactTextString(m) }
func (*GetArtifacts) ProtoMessage()    {}
func (*GetArtifacts) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{10}
}

func (m *GetArtifacts) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetArtifacts.Unmarshal(m, b)
}
func (m *GetArtifacts) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetArtifacts.Marshal(b, m, deterministic)
}
func (m *GetArtifacts) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetArtifacts.Merge(m, src)
}
func (m *GetArtifacts) XXX_Size() int {
	return xxx_messageInfo_GetArtifacts.Size(m)
}
func (m *GetArtifacts) XXX_DiscardUnknown() {
	xxx_messageInfo_GetArtifacts.DiscardUnknown(m)
}

var xxx_messageInfo_GetArtifacts proto.InternalMessageInfo

func (m *GetArtifacts) GetId() string {
	if m != nil {
		return m.Id
	}
	return ""
}

func (m *GetArtifacts) GetKey() string {
	if m != nil {
		return m.Key
	}
	return ""
}

type GetArtifacts_Response struct {
	Artifacts            []*common.Artifact `protobuf:"bytes,1,rep,name=artifacts,proto3" json:"artifacts,omitempty"`
	XXX_NoUnkeyedLiteral struct{}           `json:"-"`
	XXX_unrecognized     []byte             `json:"-"`
	XXX_sizecache        int32              `json:"-"`
}

func (m *GetArtifacts_Response) Reset()         { *m = GetArtifacts_Response{} }
func (m *GetArtifacts_Response) String() string { return proto.CompactTextString(m) }
func (*GetArtifacts_Response) ProtoMessage()    {}
func (*GetArtifacts_Response) Descriptor() ([]byte, []int) {
	return fileDescriptor_03ac4c36a5d89ec4, []int{10, 0}
}

func (m *GetArtifacts_Response) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_GetArtifacts_Response.Unmarshal(m, b)
}
func (m *GetArtifacts_Response) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_GetArtifacts_Response.Marshal(b, m, deterministic)
}
func (m *GetArtifacts_Response) XXX_Merge(src proto.Message) {
	xxx_messageInfo_GetArtifacts_Response.Merge(m, src)
}
func (m *GetArtifacts_Response) XXX_Size() int {
	return xxx_messageInfo_GetArtifacts_Response.Size(m)
}
func (m *GetArtifacts_Response) XXX_DiscardUnknown() {
	xxx_messageInfo_GetArtifacts_Response.DiscardUnknown(m)
}

var xxx_messageInfo_GetArtifacts_Response proto.InternalMessageInfo

func (m *GetArtifacts_Response) GetArtifacts() []*common.Artifact {
	if m != nil {
		return m.Artifacts
	}
	return nil
}

func init() {
	proto.RegisterType((*Feature)(nil), "ai.verta.modeldb.Feature")
	proto.RegisterType((*GetAttributes)(nil), "ai.verta.modeldb.GetAttributes")
	proto.RegisterType((*GetAttributes_Response)(nil), "ai.verta.modeldb.GetAttributes.Response")
	proto.RegisterType((*AddAttributes)(nil), "ai.verta.modeldb.AddAttributes")
	proto.RegisterType((*AddAttributes_Response)(nil), "ai.verta.modeldb.AddAttributes.Response")
	proto.RegisterType((*GetTags)(nil), "ai.verta.modeldb.GetTags")
	proto.RegisterType((*GetTags_Response)(nil), "ai.verta.modeldb.GetTags.Response")
	proto.RegisterType((*CodeVersion)(nil), "ai.verta.modeldb.CodeVersion")
	proto.RegisterType((*GitSnapshot)(nil), "ai.verta.modeldb.GitSnapshot")
	proto.RegisterType((*GetUrlForArtifact)(nil), "ai.verta.modeldb.GetUrlForArtifact")
	proto.RegisterType((*GetUrlForArtifact_Response)(nil), "ai.verta.modeldb.GetUrlForArtifact.Response")
	proto.RegisterMapType((map[string]string)(nil), "ai.verta.modeldb.GetUrlForArtifact.Response.FieldsEntry")
	proto.RegisterType((*CommitArtifactPart)(nil), "ai.verta.modeldb.CommitArtifactPart")
	proto.RegisterType((*CommitArtifactPart_Response)(nil), "ai.verta.modeldb.CommitArtifactPart.Response")
	proto.RegisterType((*GetCommittedArtifactParts)(nil), "ai.verta.modeldb.GetCommittedArtifactParts")
	proto.RegisterType((*GetCommittedArtifactParts_Response)(nil), "ai.verta.modeldb.GetCommittedArtifactParts.Response")
	proto.RegisterType((*CommitMultipartArtifact)(nil), "ai.verta.modeldb.CommitMultipartArtifact")
	proto.RegisterType((*CommitMultipartArtifact_Response)(nil), "ai.verta.modeldb.CommitMultipartArtifact.Response")
	proto.RegisterType((*GetArtifacts)(nil), "ai.verta.modeldb.GetArtifacts")
	proto.RegisterType((*GetArtifacts_Response)(nil), "ai.verta.modeldb.GetArtifacts.Response")
}

func init() {
	proto.RegisterFile("modeldb/CommonService.proto", fileDescriptor_03ac4c36a5d89ec4)
}

var fileDescriptor_03ac4c36a5d89ec4 = []byte{
	// 807 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0x8c, 0x55, 0xcd, 0x6e, 0xdb, 0x46,
	0x10, 0x2e, 0x25, 0x45, 0xb6, 0x86, 0x96, 0xe1, 0x6c, 0x8b, 0x46, 0x61, 0x93, 0x56, 0x20, 0x5a,
	0x40, 0x27, 0x0a, 0x70, 0x0f, 0x8d, 0x7b, 0x48, 0x21, 0x39, 0xb6, 0x5a, 0xa4, 0x3f, 0x06, 0xed,
	0xf8, 0xd0, 0x0b, 0xb1, 0x12, 0x47, 0xd4, 0x42, 0x24, 0x97, 0xd8, 0x5d, 0x0a, 0x20, 0xd0, 0x73,
	0xaf, 0xbd, 0xf4, 0xde, 0x5b, 0x1f, 0xa2, 0x6f, 0xd2, 0xb7, 0x09, 0x76, 0x49, 0x4a, 0xb4, 0x1d,
	0xc7, 0xbe, 0xcd, 0xcc, 0x7e, 0xf3, 0xf1, 0x9b, 0x99, 0xdd, 0x21, 0x7c, 0x91, 0xf0, 0x10, 0xe3,
	0x70, 0x3e, 0x3e, 0xe5, 0x49, 0xc2, 0xd3, 0x4b, 0x14, 0x1b, 0xb6, 0x40, 0x2f, 0x13, 0x5c, 0x71,
	0x72, 0x44, 0x99, 0xb7, 0x41, 0xa1, 0xa8, 0x57, 0xa1, 0x9c, 0x17, 0x11, 0xe7, 0x51, 0x8c, 0x63,
	0x73, 0x3e, 0xcf, 0x97, 0x63, 0xa9, 0x44, 0xbe, 0x50, 0x25, 0xde, 0x71, 0x16, 0x86, 0xe4, 0x43,
	0x5c, 0xee, 0x4b, 0xd8, 0x3b, 0x47, 0xaa, 0x72, 0x81, 0x84, 0x40, 0x27, 0xa5, 0x09, 0x0e, 0xac,
	0xa1, 0x35, 0xea, 0xf9, 0xc6, 0x76, 0xff, 0xb5, 0xa0, 0x3f, 0x43, 0x35, 0x51, 0x4a, 0xb0, 0x79,
	0xae, 0x50, 0x92, 0x43, 0x68, 0xb1, 0xb0, 0xc2, 0xb4, 0x58, 0x48, 0xbe, 0x81, 0x43, 0x5a, 0x9f,
	0x06, 0x6b, 0x2c, 0xe4, 0xa0, 0x35, 0x6c, 0x8f, 0x7a, 0x7e, 0x7f, 0x1b, 0x7d, 0x8b, 0x85, 0x24,
	0xcf, 0x60, 0x2f, 0x42, 0x15, 0xd0, 0x38, 0x1e, 0xb4, 0x87, 0xd6, 0x68, 0xdf, 0xef, 0x46, 0xa8,
	0x26, 0x71, 0xec, 0x9c, 0xc1, 0xbe, 0x8f, 0x32, 0xe3, 0xa9, 0x44, 0x72, 0x02, 0xb0, 0xcd, 0x92,
	0x03, 0x6b, 0xd8, 0x1e, 0xd9, 0xc7, 0xcf, 0xbd, 0x6d, 0xb5, 0x65, 0x19, 0xde, 0x5b, 0x2c, 0xae,
	0x69, 0x9c, 0xa3, 0xdf, 0x00, 0xbb, 0x7f, 0x40, 0x7f, 0x12, 0x86, 0x1f, 0xd1, 0xf9, 0x1d, 0xf4,
	0xb6, 0xf0, 0x41, 0x6b, 0x68, 0x7d, 0x9c, 0x7a, 0x87, 0x75, 0xdc, 0x86, 0xc0, 0xcf, 0xa1, 0x2b,
	0x15, 0x55, 0xb9, 0x34, 0xc4, 0xfb, 0x7e, 0xe5, 0xb9, 0x27, 0xb0, 0x37, 0x43, 0x75, 0x45, 0xa3,
	0x3b, 0xdf, 0x75, 0xbe, 0x6c, 0xa4, 0x13, 0xe8, 0x28, 0x1a, 0x95, 0x95, 0xf5, 0x7c, 0x63, 0xbb,
	0xff, 0x59, 0x60, 0x9f, 0xf2, 0x10, 0xaf, 0x51, 0x48, 0xc6, 0x53, 0x32, 0x85, 0x83, 0x88, 0xa9,
	0x40, 0xa6, 0x34, 0x93, 0x2b, 0xae, 0x0c, 0x93, 0x7d, 0xfc, 0xd2, 0xbb, 0x3d, 0x73, 0x6f, 0xc6,
	0xd4, 0x65, 0x05, 0xfa, 0xf1, 0x13, 0xdf, 0x8e, 0x76, 0x2e, 0x79, 0x0d, 0x07, 0x0b, 0x1e, 0x62,
	0x40, 0xc5, 0x62, 0xc5, 0x36, 0xf7, 0x97, 0x3b, 0x11, 0x8a, 0x2d, 0xe9, 0xc2, 0xe4, 0xeb, 0x84,
	0x49, 0x89, 0x27, 0x5f, 0x81, 0x1d, 0x52, 0x85, 0x41, 0xcc, 0xa3, 0x08, 0x43, 0x33, 0xb0, 0x8e,
	0x0f, 0x3a, 0xf4, 0xb3, 0x89, 0x4c, 0xbb, 0xd0, 0xd1, 0x78, 0xf7, 0x6f, 0x0b, 0xec, 0x86, 0x0e,
	0xf2, 0x02, 0x7a, 0x4b, 0x16, 0x63, 0x46, 0xd5, 0xaa, 0xae, 0x72, 0x17, 0xd0, 0xe5, 0x0b, 0xcc,
	0xb8, 0x91, 0xd3, 0xf3, 0x8d, 0xad, 0x63, 0x2b, 0x2a, 0x57, 0xe6, 0x1b, 0x3d, 0xdf, 0xd8, 0xe4,
	0x07, 0xd8, 0x67, 0x32, 0x08, 0x99, 0x50, 0xc5, 0xa0, 0x33, 0xb4, 0x46, 0x87, 0xc7, 0x5f, 0xdf,
	0x91, 0x7e, 0x85, 0x22, 0xa5, 0xa2, 0x38, 0x4b, 0xf3, 0xa4, 0xb6, 0xfd, 0x3d, 0x26, 0xdf, 0xe8,
	0x24, 0xf7, 0x9f, 0x36, 0x3c, 0x9d, 0xa1, 0x7a, 0x27, 0xe2, 0x73, 0x2e, 0xea, 0x22, 0xef, 0xdc,
	0x88, 0x23, 0x68, 0xaf, 0xb1, 0xa8, 0xd4, 0x68, 0x53, 0x8f, 0x37, 0x41, 0xb5, 0xe2, 0x61, 0x25,
	0xa7, 0xf2, 0xc8, 0x25, 0xf4, 0x69, 0xc5, 0x12, 0xa8, 0x22, 0xc3, 0x4a, 0x95, 0x77, 0x6f, 0x43,
	0xaf, 0x8a, 0x0c, 0x8d, 0xb4, 0x66, 0xc0, 0x3f, 0xa0, 0x0d, 0x4f, 0x37, 0x39, 0xa3, 0x42, 0x05,
	0x69, 0x9e, 0xcc, 0x51, 0x0c, 0x9e, 0x94, 0x4d, 0xd6, 0xa1, 0x5f, 0x4d, 0xc4, 0xf9, 0xdf, 0x6a,
	0x5c, 0x9d, 0x23, 0x68, 0xe7, 0x22, 0xae, 0xd4, 0x6b, 0x93, 0x5c, 0x40, 0x77, 0xc9, 0x30, 0x0e,
	0xcb, 0x07, 0x67, 0x1f, 0xbf, 0xfa, 0xc0, 0x15, 0xb9, 0xdd, 0x03, 0xaf, 0xe6, 0xf3, 0xce, 0x4d,
	0xea, 0x59, 0xaa, 0x44, 0xe1, 0x57, 0x3c, 0xc4, 0x83, 0x4f, 0x93, 0x3c, 0x56, 0xcc, 0xc8, 0xca,
	0xb3, 0x98, 0xd3, 0x30, 0xe0, 0xeb, 0xea, 0xbd, 0x3e, 0xdd, 0x1e, 0xbd, 0x33, 0x27, 0xbf, 0xad,
	0x9d, 0x13, 0xb0, 0x1b, 0x34, 0x75, 0x3f, 0xad, 0x5d, 0x3f, 0x3f, 0x83, 0x27, 0x1b, 0xfd, 0x9c,
	0xaa, 0x1e, 0x97, 0xce, 0xf7, 0xad, 0x57, 0x96, 0xfb, 0xa7, 0x05, 0x44, 0xaf, 0x23, 0xa6, 0x6a,
	0x69, 0x17, 0x54, 0x3c, 0x66, 0x44, 0xd3, 0xc6, 0x28, 0xb4, 0x18, 0xa3, 0xee, 0xc6, 0xfb, 0xb8,
	0x35, 0x0a, 0xcd, 0xbb, 0xeb, 0xbc, 0xf6, 0x1c, 0xd8, 0xf5, 0xd5, 0xfd, 0xcb, 0x82, 0xe7, 0x33,
	0x54, 0xa5, 0x16, 0x85, 0x61, 0x33, 0x4d, 0x3e, 0xac, 0xc7, 0xb9, 0x68, 0xcc, 0xe8, 0x0d, 0x1c,
	0xde, 0xd0, 0x56, 0xaf, 0xb0, 0x07, 0xc4, 0xf5, 0x9b, 0xe2, 0xa4, 0x3b, 0x83, 0x67, 0xa5, 0x9a,
	0x5f, 0xea, 0x86, 0x3f, 0xfe, 0x06, 0xdf, 0x28, 0x2d, 0x87, 0x03, 0xbd, 0xba, 0xab, 0xe4, 0xc7,
	0x14, 0x73, 0xda, 0x28, 0x46, 0xef, 0xcb, 0x3a, 0xf5, 0xde, 0x55, 0x5c, 0x93, 0xfb, 0x3b, 0xec,
	0x74, 0x7a, 0x61, 0xfd, 0xfe, 0x3a, 0x62, 0x6a, 0x95, 0xcf, 0x35, 0x6c, 0x7c, 0xad, 0x13, 0x26,
	0x3f, 0x8d, 0xeb, 0x3f, 0x9a, 0xf9, 0xef, 0xc8, 0x71, 0x84, 0xe9, 0x38, 0xe2, 0xb5, 0x97, 0xe5,
	0xf3, 0x98, 0x2d, 0x6a, 0xcc, 0xbc, 0x6b, 0xc2, 0xdf, 0xbe, 0x0f, 0x00, 0x00, 0xff, 0xff, 0xc3,
	0xf1, 0x9b, 0x94, 0x07, 0x07, 0x00, 0x00,
}
