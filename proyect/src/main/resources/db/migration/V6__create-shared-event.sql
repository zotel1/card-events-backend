create table shared_event (
id bigint auto_increment primary key,
event_id bigint not null,
shared_with_user_id varchar(255) not null,
foreign key (event_id) references event(id),
foreign key (shared_with_user_id) references user(id)
);