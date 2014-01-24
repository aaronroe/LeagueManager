# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table athlete (
  id                        bigint not null,
  team_id                   bigint,
  portrait                  varchar(255),
  which_game_id             bigint,
  solo_queue_rating         integer,
  morale                    double,
  name                      varchar(255),
  base_reflexes             double,
  base_concentration        double,
  base_hand_eye_coordination double,
  base_perception           double,
  base_intelligence         double,
  base_wits                 double,
  base_resolution           double,
  potential                 double,
  experience                double,
  luck                      double,
  constraint ck_athlete_solo_queue_rating check (solo_queue_rating in (0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25)),
  constraint pk_athlete primary key (id))
;

create table champion_affinity (
  id                        bigint not null,
  champion_name             varchar(255),
  strength                  double,
  constraint pk_champion_affinity primary key (id))
;

create table game (
  id                        bigint not null,
  owner_id                  bigint,
  user_team_id              bigint,
  is_team_init              boolean,
  is_roster_init            boolean,
  money                     integer,
  date                      bigint,
  constraint pk_game primary key (id))
;

create table lane_affinity (
  id                        bigint not null,
  lane_name                 varchar(255),
  strength                  double,
  constraint pk_lane_affinity primary key (id))
;

create table linked_account (
  id                        bigint not null,
  user_id                   bigint,
  provider_user_id          varchar(255),
  provider_key              varchar(255),
  constraint pk_linked_account primary key (id))
;

create table security_role (
  id                        bigint not null,
  role_name                 varchar(255),
  constraint pk_security_role primary key (id))
;

create table team (
  id                        bigint not null,
  which_game_id             bigint,
  name                      varchar(255),
  logo                      varchar(255),
  constraint pk_team primary key (id))
;

create table token_action (
  id                        bigint not null,
  token                     varchar(255),
  target_user_id            bigint,
  type                      varchar(2),
  created                   timestamp,
  expires                   timestamp,
  constraint ck_token_action_type check (type in ('EV','PR')),
  constraint uq_token_action_token unique (token),
  constraint pk_token_action primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  last_login                timestamp,
  active                    boolean,
  email_validated           boolean,
  constraint pk_users primary key (id))
;

create table user_permission (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_user_permission primary key (id))
;


create table athlete_champion_affinity (
  athlete_id                     bigint not null,
  champion_affinity_id           bigint not null,
  constraint pk_athlete_champion_affinity primary key (athlete_id, champion_affinity_id))
;

create table athlete_lane_affinity (
  athlete_id                     bigint not null,
  lane_affinity_id               bigint not null,
  constraint pk_athlete_lane_affinity primary key (athlete_id, lane_affinity_id))
;

create table users_security_role (
  users_id                       bigint not null,
  security_role_id               bigint not null,
  constraint pk_users_security_role primary key (users_id, security_role_id))
;

create table users_user_permission (
  users_id                       bigint not null,
  user_permission_id             bigint not null,
  constraint pk_users_user_permission primary key (users_id, user_permission_id))
;
create sequence athlete_seq;

create sequence champion_affinity_seq;

create sequence game_seq;

create sequence lane_affinity_seq;

create sequence linked_account_seq;

create sequence security_role_seq;

create sequence team_seq;

create sequence token_action_seq;

create sequence users_seq;

create sequence user_permission_seq;

alter table athlete add constraint fk_athlete_team_1 foreign key (team_id) references team (id) on delete restrict on update restrict;
create index ix_athlete_team_1 on athlete (team_id);
alter table athlete add constraint fk_athlete_whichGame_2 foreign key (which_game_id) references game (id) on delete restrict on update restrict;
create index ix_athlete_whichGame_2 on athlete (which_game_id);
alter table game add constraint fk_game_owner_3 foreign key (owner_id) references users (id) on delete restrict on update restrict;
create index ix_game_owner_3 on game (owner_id);
alter table game add constraint fk_game_userTeam_4 foreign key (user_team_id) references team (id) on delete restrict on update restrict;
create index ix_game_userTeam_4 on game (user_team_id);
alter table linked_account add constraint fk_linked_account_user_5 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_linked_account_user_5 on linked_account (user_id);
alter table team add constraint fk_team_whichGame_6 foreign key (which_game_id) references game (id) on delete restrict on update restrict;
create index ix_team_whichGame_6 on team (which_game_id);
alter table token_action add constraint fk_token_action_targetUser_7 foreign key (target_user_id) references users (id) on delete restrict on update restrict;
create index ix_token_action_targetUser_7 on token_action (target_user_id);



alter table athlete_champion_affinity add constraint fk_athlete_champion_affinity__01 foreign key (athlete_id) references athlete (id) on delete restrict on update restrict;

alter table athlete_champion_affinity add constraint fk_athlete_champion_affinity__02 foreign key (champion_affinity_id) references champion_affinity (id) on delete restrict on update restrict;

alter table athlete_lane_affinity add constraint fk_athlete_lane_affinity_athl_01 foreign key (athlete_id) references athlete (id) on delete restrict on update restrict;

alter table athlete_lane_affinity add constraint fk_athlete_lane_affinity_lane_02 foreign key (lane_affinity_id) references lane_affinity (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_users_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_securi_02 foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_user_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_user_02 foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists athlete;

drop table if exists athlete_champion_affinity;

drop table if exists athlete_lane_affinity;

drop table if exists champion_affinity;

drop table if exists game;

drop table if exists lane_affinity;

drop table if exists linked_account;

drop table if exists security_role;

drop table if exists team;

drop table if exists token_action;

drop table if exists users;

drop table if exists users_security_role;

drop table if exists users_user_permission;

drop table if exists user_permission;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists athlete_seq;

drop sequence if exists champion_affinity_seq;

drop sequence if exists game_seq;

drop sequence if exists lane_affinity_seq;

drop sequence if exists linked_account_seq;

drop sequence if exists security_role_seq;

drop sequence if exists team_seq;

drop sequence if exists token_action_seq;

drop sequence if exists users_seq;

drop sequence if exists user_permission_seq;

