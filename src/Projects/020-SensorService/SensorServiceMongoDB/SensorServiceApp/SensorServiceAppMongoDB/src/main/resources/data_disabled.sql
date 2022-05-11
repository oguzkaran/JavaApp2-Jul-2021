insert into members (username, password, enabled) values ('csd', '{bcrypt}$2a$10$TszUkcOKwhBzXtPAj8lF7ea1dPdSMRBXqNrcR7y9LteW8QYr/Kul6', true); --csd1993 -- {encrypt type} encrypt hash
insert into members (username, password, enabled) values ('oguzhan', '{bcrypt}$2a$10$4SBvgn6elc34Y2.QtxT3DOJ4dkHfNrXV5a.DKB9gwVwFtwktKwbeW', true); --oguzhan1993 --

insert into member_roles (member_id, role) values (1, 'ADMIN'), (2, 'USER');
