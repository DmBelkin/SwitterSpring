
CREATE TABLE message
(
  `id`       int not null,
  `filename` varchar(255),
  `tag`      varchar(255),
  `text`     varchar(2048) not null ,
  `user_id`  bigint,
primary key(id)
);

create table `message_seq`
(
  `next_val` bigint
);

insert into `message_seq`
values (1);

create table `user`
(
  `id`            int not null,
  activation_code varchar(255),
  `active`        boolean  not null,
  `email`         varchar(255) not null ,
  `password`      varchar(255) not null ,
  `username`      varchar(255) not null ,
primary key(id)
);

create table `user_role`
(
  `user_id` bigint not null,
  role      varchar(255)
);

create table `user_seq`
(
  `next_val` bigint
);

insert into `user_seq`
values (1);

insert into `user`
values (1, 0, true, "some@some.com" ,123, "sidor" );

insert into `user_role`
values (1 ,  "ADMIN");

alter table message
add constraint FKb3y6etti1cfougkdr0qiiemgv foreign key (`user_id`) references user (id);

alter table user_role
add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (`user_id`) references user (id)