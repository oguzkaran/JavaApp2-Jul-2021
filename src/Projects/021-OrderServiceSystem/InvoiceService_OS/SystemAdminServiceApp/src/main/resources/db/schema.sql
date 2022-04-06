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
