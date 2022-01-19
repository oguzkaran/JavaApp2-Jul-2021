drop table member_roles;
drop table members;

create table members (
    member_id serial primary key,
    username varchar(50) unique not null,
    password varchar(50) not null,
    enabled boolean not null
);

create table member_roles (
    member_role_id serial primary key,
    member_id int references members(member_id) not null,
    role varchar(50) not null
);


