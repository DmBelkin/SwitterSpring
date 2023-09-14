insert into `user` (id, username, password, active, email, activation_code)
values (1, 'vasya', '123456', true, 'some@some.ru', '');

insert into `user_role` (user_id, role)
values (1, 'USER'), (1, 'ADMIN);


