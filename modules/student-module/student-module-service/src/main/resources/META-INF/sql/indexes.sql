create index IX_BA15464C on TEST_Student (firstName[$COLUMN_LENGTH:75$], lastName[$COLUMN_LENGTH:75$]);
create index IX_1D53376 on TEST_Student (groupId);
create index IX_9686A8A8 on TEST_Student (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E771392A on TEST_Student (uuid_[$COLUMN_LENGTH:75$], groupId);