-- japp2j21_invoicesdb

create table invoices (
    invoice_id bigserial primary key,
    customer_title varchar(512) not null,
    customer_identity varchar(100) not null,
    address varchar(1024) not null,
    invoice_date date not null,
    invoice_tax_ratio real default(18.0) not null
);


create table orders (
    order_id bigserial primary key,
    invoice_id bigint references invoices(invoice_id) not null,
    code varchar(100) not null,
    name varchar(100) not null,
    quantity real not null,
    unit_price real not null
);

