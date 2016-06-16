# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table mail (
  id                            bigint not null,
  category                      varchar(10),
  sent_from                     varchar(255),
  subject                       varchar(255),
  body                          varchar(255),
  create_time                   timestamp not null,
  last_update                   timestamp not null,
  constraint ck_mail_category check (category in ('SOCIAL','NOT_SOCIAL')),
  constraint pk_mail primary key (id)
);
create sequence mail_seq;


# --- !Downs

drop table if exists mail;
drop sequence if exists mail_seq;

