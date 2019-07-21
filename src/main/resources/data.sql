insert into "user"(user_id, password, name, active) values(1, '$2a$10$vRd//15UkKriJ7j3ZhVjGO/KfL6pm9wYEnyUWFroT1y/DTXlOm9Xa', 'user',1);
insert into "user"(user_id, password, name, active) values(2, '$2a$10$ISkiAGNspZzBjwzVmzLhOOmk.2YMudsRIJNiOlee94OjUAapV.LbK', 'admin',1);

insert into role(role_id, role) values(1, 'ADMIN');
insert into role(role_id, role) values(2, 'USER');
insert into role(role_id, role) values(3, 'GUEST');

insert into user_role(user_id, role_id) values(1, 1);
insert into user_role(user_id, role_id) values(1, 2);
insert into user_role(user_id, role_id) values(1, 3);

insert into user_role(user_id, role_id) values(2, 1);
insert into user_role(user_id, role_id) values(2, 2);
insert into user_role(user_id, role_id) values(2, 3);
