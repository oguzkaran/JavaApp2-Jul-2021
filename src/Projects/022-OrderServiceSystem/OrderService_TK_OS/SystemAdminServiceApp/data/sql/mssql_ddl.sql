create database japp2j21_oss_ordersdb

go

use japp2j21_oss_ordersdb

go

CREATE TABLE t_application_users (
    id int PRIMARY KEY identity(1, 1),
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(100) NOT NULL,
    enabled BIT NOT NULL,
    deleted BIT,
    description NVARCHAR(512),
    created_date_time DATETIME NOT NULL,
    updated_date_time DATETIME NOT NULL,
    deleted_date_time DATETIME
)

go

CREATE TABLE t_application_user_roles (
    id int PRIMARY KEY identity(1, 1),
    application_user_id INT foreign key references t_application_users(id) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    deleted BIT,
    description NVARCHAR(512),
    created_date_time DATETIME NOT NULL,
    updated_date_time DATETIME NOT NULL,
    deleted_date_time DATETIME
);

