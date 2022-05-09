-- default system user csd:csd1993
INSERT INTO t_application_users (username, password, enabled,deleted, created_date_time, updated_date_time, deleted_date_time, description)
VALUES ('csdr', '$2a$10$9p0Efw6X3mkn9MaWWqpz8ex34vAHj0C9oT7NoDfzD3pGKmMk8VmMa', true, null, current_timestamp, current_timestamp, null, 'SYSTEM DEFAULT ROOT USER'); --csd1993

-- default application user role
INSERT INTO t_application_user_roles (application_user_id, role_name, deleted, created_date_time, updated_date_time, deleted_date_time, description)
VALUES (1, 'ROLE_SYSTEM_ADMIN',null, current_timestamp, current_timestamp, null, 'SYSTEM DEFAULT ROOT USER ROLE');
VALUES (1, 'ROLE_SYSTEM_ADMIN',null, current_timestamp, current_timestamp, null, 'SYSTEM DEFAULT ROOT USER ROLE');


