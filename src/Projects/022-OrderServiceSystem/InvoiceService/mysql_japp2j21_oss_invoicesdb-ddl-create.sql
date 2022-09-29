-- japp2j21_oss_invoicesdb

create table invoices (
    invoice_id int primary key auto_increment,
    customer_title varchar(512) not null,
    customer_identity varchar(100) not null,
    address varchar(1024) not null,
    invoice_date date not null,
    invoice_tax_ratio real default(18.0) not null
);


create table orders (
    order_id int primary key auto_increment,
    invoice_id int ,
    code varchar(100) not null,
    name varchar(100) not null,
    quantity real not null,
    unit_price real not null,
    foreign key (invoice_id) references invoices(invoice_id)
);

CREATE TABLE t_application_users (
    id int PRIMARY KEY auto_increment,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
    deleted BOOLEAN,
    description VARCHAR(512),
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);

CREATE TABLE t_application_user_roles (
    id int PRIMARY KEY auto_increment,
    application_user_id INT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    deleted BOOLEAN,
    description VARCHAR(512),
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP,
    foreign key (application_user_id) references t_application_users(id)
);

