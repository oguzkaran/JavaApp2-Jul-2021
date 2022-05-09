create database japp2j21_oss_ordersdb

go

use japp2j21_oss_ordersdb


go

create table orders (
	order_id bigint primary key identity(1, 1),
	odatetime datetime default(SYSDATETIME()) not null,
	client_id int not null
)

go

create table orderproducts (
	order_product_id bigint primary key identity(1, 1),
	order_id bigint foreign key references orders(order_id) not null,
	product_id int not null,
	quantity real not null,
	unit_price real not null
)

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

