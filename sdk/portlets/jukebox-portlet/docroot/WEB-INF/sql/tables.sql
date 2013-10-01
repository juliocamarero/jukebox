create table jukebox_Album (
	uuid_ VARCHAR(75) null,
	albumId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	artistId LONG,
	name VARCHAR(75) null,
	year INTEGER
);

create table jukebox_Artist (
	uuid_ VARCHAR(75) null,
	artistId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);

create table jukebox_Song (
	uuid_ VARCHAR(75) null,
	songId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	artistId LONG,
	albumId LONG,
	name VARCHAR(75) null
);