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

