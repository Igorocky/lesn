# --- !Ups

create table "USERS" (
  "id" BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  "role" INTEGER NOT NULL,
  "login" VARCHAR NOT NULL,
  "salt" VARCHAR NOT NULL,
  "passHash" VARCHAR NOT NULL
);

# --- !Downs

drop table "USERS";
