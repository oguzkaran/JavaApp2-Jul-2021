-- Default user tables
create table users (
    username varchar(50) primary key not null,
    password varchar(100) not null,
    enabled smallint not null
);

create table authorities (
    authority_id serial primary key,
    username varchar(50) references users(username),
    authority varchar(50) not null
);






-- ROLE_SYS_ADMIN, ROLE_ADMIN, ROLE_SYSTEM, ROLE_USER, ROLE_VIEWER



