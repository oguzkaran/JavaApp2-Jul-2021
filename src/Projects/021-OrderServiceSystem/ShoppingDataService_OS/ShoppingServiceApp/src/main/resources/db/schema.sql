CREATE TABLE IF NOT EXISTS t_customers (
     id SERIAL PRIMARY KEY,
     identity VARCHAR(100) UNIQUE NOT NULL,
     title VARCHAR(512) NOT NULL,
     email VARCHAR(50) NOT NULL,
     address VARCHAR(1024) NOT NULL,
     register_date DATE DEFAULT(current_date) NOT NULL,
     active BOOLEAN DEFAULT(true) NOT NULL,
     deleted BOOLEAN,
     created_date_time TIMESTAMP NOT NULL,
     updated_date_time TIMESTAMP  NOT NULL,
     deleted_date_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_phone_types (
    id SERIAL PRIMARY KEY,
    description VARCHAR(20) NOT NULL,
    deleted BOOLEAN,
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);


CREATE TABLE IF NOT EXISTS t_phones (
    id BIGSERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    phone_type_id INT NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    deleted BOOLEAN,
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_product_categories (
    id SERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    deleted BOOLEAN,
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_products (
    id SERIAL PRIMARY KEY,
    stock_number VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    product_category_id INT NOT NULL,
    stock DOUBLE PRECISION NOT NULL,
    cost DOUBLE PRECISION NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    image_path VARCHAR(1024) NOT NULL,
    deleted BOOLEAN,
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);

ALTER TABLE t_phones ADD FOREIGN KEY (customer_id) REFERENCES t_customers(id);
ALTER TABLE t_phones ADD FOREIGN KEY (phone_type_id) REFERENCES t_phone_types(id);
ALTER TABLE t_products ADD FOREIGN KEY (product_category_id) REFERENCES t_product_categories(id);
