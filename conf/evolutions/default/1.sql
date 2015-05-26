# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table good_count (
  user_id                   bigint not null,
  card_id                   bigint not null)
;

create table help_category (
  category_id               bigint not null,
  category_name             varchar(255),
  constraint pk_help_category primary key (category_id))
;

create table section (
  section_id                bigint not null,
  section_name              varchar(255),
  constraint pk_section primary key (section_id))
;

create table thanks_card (
  card_id                   bigint not null,
  help_text                 varchar(255),
  thanks_text               varchar(255),
  send_day                  timestamp,
  delete_request            integer,
  help_date                 timestamp,
  category_id               bigint,
  send_id                   bigint,
  receive_id                bigint,
  constraint pk_thanks_card primary key (card_id))
;

create table user (
  user_id                   bigint not null,
  user_cd                   varchar(255),
  user_name                 varchar(255),
  user_password             varchar(255),
  permission                integer,
  section_id                bigint,
  constraint pk_user primary key (user_id))
;

create sequence good_count_seq;

create sequence help_category_seq;

create sequence section_seq;

create sequence thanks_card_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists good_count;

drop table if exists help_category;

drop table if exists section;

drop table if exists thanks_card;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists good_count_seq;

drop sequence if exists help_category_seq;

drop sequence if exists section_seq;

drop sequence if exists thanks_card_seq;

drop sequence if exists user_seq;

