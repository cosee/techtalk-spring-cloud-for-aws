-- noinspection SqlNoDataSourceInspectionForFile

create table candy (
  id       VARCHAR(255) NOT NULL PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  price    INT          NULL,
  calories INT          NULL
);

insert into candy (id, name, price, calories)
values ('1', 'Chocolate Bar', 100, 250);

insert into candy (id, name, price, calories)
values ('3', 'Gummy Bears', 150, 150);

insert into candy (id, name, price, calories)
values ('12', 'T-Bone Steak', 2000, 1000);


create table order_item (
  id         VARCHAR(255) NOT NULL PRIMARY KEY,
  order_id   VARCHAR(255) NOT NULL,
  candy_id   INT          NOT NULL,
  candy_name VARCHAR(255) NOT NULL,
  price       INT          NOT NULL,
  amount     INT          NOT NULL
);