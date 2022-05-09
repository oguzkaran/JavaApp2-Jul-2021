CREATE TABLE IF NOT EXISTS t_application_users (
    id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
    deleted BOOLEAN,
    description VARCHAR(512),
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP NOT NULL,
    deleted_date_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_application_user_roles (
    id SERIAL PRIMARY KEY,
    application_user_id INT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    deleted BOOLEAN,
    description VARCHAR(512),
    created_date_time TIMESTAMP NOT NULL,
    updated_date_time TIMESTAMP  NOT NULL,
    deleted_date_time TIMESTAMP
);


-- CONSTRAINTS
ALTER TABLE t_application_user_roles ADD FOREIGN KEY (application_user_id) REFERENCES t_application_users(id);
