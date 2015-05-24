# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table user (
  user_id                   bigint not null,
  user_name                 varchar(255),
  user_password             varchar(255),
  permission                integer,
  section_section_id        bigint,
  constraint pk_user primary key (user_id))
;

create sequence help_category_seq;

create sequence section_seq;

create sequence user_seq;

alter table user add constraint fk_user_section_1 foreign key (section_section_id) references section (section_id) on delete restrict on update restrict;
create index ix_user_section_1 on user (section_section_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists help_category;

drop table if exists section;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists help_category_seq;

drop sequence if exists section_seq;

drop sequence if exists user_seq;
