insert into members (username, password, enabled) values ('csd', '{noop}csd1993', true);
insert into members (username, password, enabled) values ('oguz', '{noop}csd', false);
insert into members (username, password, enabled) values ('burak', '{noop}burak123', true);

insert into member_roles (member_id, role) values (1, 'ADMIN'), (2, 'USER'), (3, 'SYSTEM');
