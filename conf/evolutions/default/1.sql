# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table good_count (
  user_name                 varchar(255),
  user_password             varchar(255),
  permission                integer,
  help_text                 varchar(255),
  thanks_text               varchar(255),
  send_day                  timestamp,
  delete_request            integer,
  help_date                 timestamp,
  send_name                 varchar(255),
  send_section              varchar(255))
;

create table help_category (
  category_id               bigint not null,
  category_name             varchar(255),
  card_card_id              integer,
  constraint pk_help_category primary key (category_id))
;

create table section (
  section_id                bigint not null,
  section_name              varchar(255),
  constraint pk_section primary key (section_id))
;

create table thanks_card (
  card_id                   integer not null,
  help_text                 varchar(255),
  thanks_text               varchar(255),
  send_day                  timestamp,
  delete_request            integer,
  help_date                 timestamp,
  receive_user_id           bigint,
  send_name                 varchar(255),
  send_section              varchar(255),
  constraint pk_thanks_card primary key (card_id))
;

create table user (
  user_id                   bigint not null,
  user_name                 varchar(255),
  user_password             varchar(255),
  permission                integer,
  section_section_id        bigint,
  constraint pk_user primary key (user_id))
;

create sequence good_count_seq;

create sequence help_category_seq;

create sequence section_seq;

create sequence thanks_card_seq;

create sequence user_seq;

alter table help_category add constraint fk_help_category_card_1 foreign key (card_card_id) references thanks_card (card_id) on delete restrict on update restrict;
create index ix_help_category_card_1 on help_category (card_card_id);
alter table thanks_card add constraint fk_thanks_card_receive_2 foreign key (receive_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_thanks_card_receive_2 on thanks_card (receive_user_id);
alter table user add constraint fk_user_section_3 foreign key (section_section_id) references section (section_id) on delete restrict on update restrict;
create index ix_user_section_3 on user (section_section_id);



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

