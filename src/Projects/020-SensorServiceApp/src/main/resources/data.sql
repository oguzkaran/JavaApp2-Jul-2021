insert into members (username, password, enabled) values ('csd', '{bcrypt}$2a$10$9p0Efw6X3mkn9MaWWqpz8ex34vAHj0C9oT7NoDfzD3pGKmMk8VmMa', true); --csd1993
insert into members (username, password, enabled) values ('oguz', '{bcrypt}$2a$10$jcpEpq3sLsTeVI6/HpnRdObb02aNiDEsANplr9aFXnpvttQgACDEO', true); -- csd
insert into members (username, password, enabled) values ('burak', '{bcrypt}$2a$10$nwLlUmnYuzYl73x5KW5TtObEuWu6esgGrvVRR/mHnbyDfbIYcZJw2', true); -- burak123

insert into member_roles (member_id, role) values (1, 'ROLE_ADMIN'), (2, 'ROLE_USER'), (3, 'ROLE_SYSTEM');
