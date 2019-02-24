create table Bittle (
	id identity,
	message varchar(140) not null,
	created_at timestamp not null,
	latitude double,
	longitude double
);

create table Bitter (
	id identity,
	username varchar(30) unique not null,
	password varchar(30) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);

insert into Bittle (message,created_at,latitude,longitude) values ('testmessage',CURRENT_TIMESTAMP,123,456);
insert into Bittle (message,created_at,latitude,longitude) values ('testmessage一个中文消息',CURRENT_TIMESTAMP,123,456);