drop table if exists inventory;
create table inventory (
    id int primary key,
    sku_code varchar(255),
    stock int
);