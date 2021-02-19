drop table if exists `list_name` CASCADE;
drop table if exists `list_items` CASCADE;
create table list_name (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null);
create table list_items (id bigint PRIMARY KEY AUTO_INCREMENT, completed boolean not null, description varchar(255) not null, name varchar(255) not null, listname_id bigint);