create table calendar (
id bigint auto_increment primary key,
name varchar(255) not null,
user_id varchar(255) not null,
foreign key (user_id) references users(id)
);