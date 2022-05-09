CREATE TABLE IF NOT EXISTS t_invoices (
    id BIGSERIAL PRIMARY KEY,
    customer_title VARCHAR(512) NOT NULL,
    customer_identity VARCHAR(100) NOT NULL,
    address VARCHAR(1024) NOT NULL,
    invoice_date DATE NOT NULL,
    invoice_tax_ratio REAL DEFAULT(18.0) NOT NULL,
    deleted BOOLEAN,
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);


CREATE TABLE IF NOT EXISTS t_orders (
    id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    code VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    quantity REAL NOT NULL,
    unit_price REAL NOT NULL,
    deleted BOOLEAN,
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);

ALTER TABLE t_orders ADD FOREIGN KEY (invoice_id) REFERENCES t_invoices(id);