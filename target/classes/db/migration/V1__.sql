create user gift@localhost identified by 'gift';

create database gift character set utf8 collate utf8_general_ci;

grant all on gift.* to gift@localhost;

use gift;

create table users
(
  id bigint auto_increment not null primary key,
  name varchar( 255 ) not null,
  surname varchar( 255 ) not null,
  email varchar( 255 ) not null,
  create_date datetime not null,
  password varchar( 255 ) not null,
  blocked bit( 1 ) not null
);