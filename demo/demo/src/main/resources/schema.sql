drop table if exists item
drop table if exists lookup

create table item(
   id integer not null auto_increment,
   name varchar(255) not null,
   price integer not null,
   category varchar(255) not null,
   primary key(id)
);

create table lookup(
   id integer not null,
   type varchar(255) not null,
   name varchar(255) not null,
   value varchar(255) not null,
   primary key(id)
);