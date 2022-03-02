create table if not exists members (
    member_id serial primary key,
    username varchar(50) unique not null,
    password varchar(100) not null,
    enabled boolean not null
);

create table if not exists member_roles (
    member_role_id serial primary key,
    member_id int references members(member_id) not null,
    role varchar(50) not null
);


create or replace function insert_member(varchar(50), varchar(100), boolean)
returns void
as $$
begin
    insert into members (username, password, enabled) values ($1, $2, $3);
end
$$ language plpgsql;

-- ROLE_SYS_ADMIN, ROLE_ADMIN, ROLE_SYSTEM, ROLE_USER, ROLE_VIEWER



