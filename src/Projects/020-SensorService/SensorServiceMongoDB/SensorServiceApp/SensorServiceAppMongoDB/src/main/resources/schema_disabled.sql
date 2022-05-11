--drop table if exists member_roles;
--drop table if exists members;

create table members(
    member_id serial primary key,
    username varchar(100) unique not null,
    password varchar(100) not null,
    enabled boolean not null
);

create table member_roles(
    member_role_id serial primary key,
    member_id int references members(member_id) not null,
    role varchar(50) not null
);