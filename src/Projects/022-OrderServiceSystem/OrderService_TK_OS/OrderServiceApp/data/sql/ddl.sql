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

drop procedure if exists sp_insert_order

go

create procedure sp_insert_order(@oDateTime datetime, @client_id int, @id bigint output)
as
begin
    begin tran
    declare @status int = 0
    insert into orders (odatetime, client_id) values (@oDateTime, @client_id)

    if @@ERROR <> 0
    begin
        set @status = @@ERROR
        goto END_TRAN
    end

    set @id = @@IDENTITY
    commit tran
END_TRAN:
    if @status <> 0
        rollback tran
end

go

drop procedure if exists sp_insert_order_client_id

go

create procedure sp_insert_order_client_id(@client_id int, @id bigint output)
as
begin
    begin tran
        declare @status int = 0
        insert into orders (client_id) values (@client_id)

        if @@ERROR <> 0
            begin
                set @status = @@ERROR
                goto END_TRAN
            end

        set @id = @@IDENTITY
    commit tran
    END_TRAN:
    if @status <> 0
        rollback tran
end
go

drop procedure if exists sp_insert_order_product

go

create procedure sp_insert_order_product(@order_id bigint, @product_id int, @quantity real, @unit_price real)
as
begin
    begin tran
        declare @status int = 0
        insert into order_products (order_id, product_id, quantity, unit_price) values (@order_id, @product_id, @quantity, @unit_price)

        if @@ERROR <> 0
        begin
            set @status = @@ERROR
            goto END_TRAN
        end
    commit tran
    END_TRAN:
    if @status <> 0
        rollback tran
end

go

drop function if exists get_orders_by_product_id

go

create function get_orders_by_product_id(@product_id int)
returns table
as
return (
    select o.order_id, o.odatetime, o.client_id
    from orders o inner join orderproducts op  on op.order_id = o.order_id
    where product_id = @product_id
)

go

create function get_orders_by_client_id(@client_id int)
returns table
as
return (
    select * from orders where client_id = @client_id
)

go

create function get_orders_by_datetime_between(@begin datetime, @end datetime)
returns table
as
return (
    select * from orders where odatetime between @begin and @end
)

go

create function get_orders_by_date(@date date)
returns table
as
return (
    select * from orders where cast(odatetime as date) = @date
)

go

create function get_orders_by_month_and_year(@month int, @year int)
returns table
as
return (
    select * from orders where DATEPART(MONTH, odatetime) = @month and  DATEPART(YEAR, odatetime) = @year
)

go

create function get_orders_by_year_between(@begin int, @end int)
returns table
as
return (
    select * from orders where DATEPART(YEAR, odatetime) between @begin and @end
)

go

create function get_order_products_by_order_id(@order_id bigint)
returns table
as
return (
    select * from orderproducts where order_id=@order_id
)

go

create function get_order_products_by_product_id(@product_id int)
returns table
as
return (
    select * from orderproducts where order_id=@product_id
)

go

