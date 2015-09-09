# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table good_count (
  count_id                  bigint not null,
  user_user_id              bigint,
  cards_card_id             bigint,
  constraint pk_good_count primary key (count_id))
;

create table help_category (
  category_id               bigint not null,
  category_name             varchar(255),
  delete                    integer,
  constraint pk_help_category primary key (category_id))
;

create table section (
  section_id                bigint not null,
  section_name              varchar(255),
  delete                    integer,
  constraint pk_section primary key (section_id))
;

create table thanks_card (
  card_id                   bigint not null,
  help_text                 varchar(255),
  thanks_text               varchar(255),
  send_day                  timestamp,
  delete_request            integer,
  help_date                 timestamp,
  category_category_id      bigint,
  receive_user_id           bigint,
  send_user_id              bigint,
  good                      integer,
  constraint pk_thanks_card primary key (card_id))
;

create table user (
  user_id                   bigint not null,
  user_cd                   varchar(255),
  user_name                 varchar(255),
  user_password             varchar(255),
  permission                integer,
  section_section_id        bigint,
  delete                    integer,
  constraint pk_user primary key (user_id))
;

create sequence good_count_seq;

create sequence help_category_seq;

create sequence section_seq;

create sequence thanks_card_seq;

create sequence user_seq;

alter table good_count add constraint fk_good_count_user_1 foreign key (user_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_good_count_user_1 on good_count (user_user_id);
alter table good_count add constraint fk_good_count_cards_2 foreign key (cards_card_id) references thanks_card (card_id) on delete restrict on update restrict;
create index ix_good_count_cards_2 on good_count (cards_card_id);
alter table thanks_card add constraint fk_thanks_card_category_3 foreign key (category_category_id) references help_category (category_id) on delete restrict on update restrict;
create index ix_thanks_card_category_3 on thanks_card (category_category_id);
alter table thanks_card add constraint fk_thanks_card_receive_4 foreign key (receive_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_thanks_card_receive_4 on thanks_card (receive_user_id);
alter table thanks_card add constraint fk_thanks_card_send_5 foreign key (send_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_thanks_card_send_5 on thanks_card (send_user_id);
alter table user add constraint fk_user_section_6 foreign key (section_section_id) references section (section_id) on delete restrict on update restrict;
create index ix_user_section_6 on user (section_section_id);



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

