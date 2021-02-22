drop table if exists `list_name` CASCADE;
drop table if exists `list_item` CASCADE;
create table list_name (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null);
create table list_item (id bigint PRIMARY KEY AUTO_INCREMENT, completed boolean not null, description varchar(255) not null, name varchar(255) not null, listname_id bigint);
alter table list_item add constraint FK6ikpg1bdaaa05dflt1e84kt63 foreign key (listname_id) references list_name on delete cascade;