-- japp2j21_oos_shoppingdb

create table customers (
    customer_id serial primary key,
    identity varchar(100) unique not null,
    title varchar(512) not null,
    email varchar(50) not null,
    address varchar(1024) not null,
    register_date date default(current_date) not null,
    active boolean default(true) not null
);

create table phone_types (
    phone_type_id serial primary key,
    description varchar(20) not null
);

insert into phone_types (description) values ('GSM'), ('WORK'), ('HOME');

create table phones (
    phone_id bigserial primary key,
    customer_id int references customers(customer_id) not null,
    phone_type_id int references phone_types(phone_type_id) not null
);

create table product_categories (
    product_category_id serial primary key,
    description varchar(50) not null
);

insert into product_categories (description) values ('FOOD'), ('WEAR'), ('ELECTRONIC');

create table products (
    product_id serial primary key,
    stock_number varchar(100) unique not null,
    name varchar(100) not null,
    product_category_id int references product_categories(product_category_id) not null,
    stock double precision not null,
    cost double precision not null,
    unit_price double precision not null,
    image_path varchar(1024) not null
);


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

