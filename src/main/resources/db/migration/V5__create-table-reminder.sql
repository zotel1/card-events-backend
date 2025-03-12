create table reminder (
id bigint auto_increment primary key,
event_id bigint not null,
reminder_time datetime not null,
foreign key (event_id) references event(id)
);