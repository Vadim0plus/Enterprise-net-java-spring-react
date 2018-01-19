create table Users (
	id identity,
	userName varchar(16) not null,
	password varchar(25) not null,
	firstName varchar(30),
	lastName varchar(30),
	email varchar(30)
);

create table Messages (
	id identity,
	message varchar(250),
	saveTime time
);
