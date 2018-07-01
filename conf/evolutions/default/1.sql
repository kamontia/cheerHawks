# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table checks (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  result                    varchar(255),
  created                   timestamp,
  modified                  timestamp,
  constraint pk_checks primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists checks;

SET REFERENTIAL_INTEGRITY TRUE;

