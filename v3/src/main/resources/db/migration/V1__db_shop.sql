-- USERS
DROP SEQUENCE IF EXISTS user_seq;
create sequence user_seq start 1 increment 1;

DROP TABLE IF EXISTS users CASCADE;

create table users (
                       id int8 not null,
                       name varchar(255),
                       lastname varchar(255),
                       firstname varchar(255),
                       password varchar(255),
                       archive boolean not null,
                       role varchar(255),
                       --bucket_id int8,
                       primary key (id)
);
-- BUCKET
DROP SEQUENCE IF EXISTS bucket_seq;
create sequence bucket_seq start 1 increment 1;

DROP TABLE IF EXISTS buckets CASCADE;

create table buckets (
                         id int8 not null,
                         user_id int8,
                         primary key (id)
);

-- LINK BETWEEN BUCKET AND USER
alter table if exists buckets -- изменим таблицу если будет наличие строк в
    add constraint buckets_fk_user -- добавляем ограничения на
    foreign key (user_id) references users; -- создание связи по внешнему ключу, и контроль на наличие в точности совпадающего по значению

-- CATEGORY
DROP SEQUENCE IF EXISTS category_seq;
create sequence category_seq start 1 increment 1;

DROP TABLE IF EXISTS categories CASCADE;

create table categories (
                            id int8 not null,
                            title varchar(255),
                            primary key (id)
);
-- PRODUCTS
DROP SEQUENCE IF EXISTS product_seq;
create sequence product_seq start 1 increment 1;

DROP TABLE IF EXISTS products CASCADE;

create table products (
                          id int8 not null,
                          vendor int4,
                          title varchar(255),
                          price numeric(19, 2),
                          amount numeric(19, 2),
                          description varchar(255),
                          primary key (id)
);

-- CATEGORY AND PRODUCT
DROP TABLE IF EXISTS products_categories CASCADE;
create table products_categories (
                                     product_id int8 not null,
                                     category_id int8 not null
);

alter table if exists products_categories
    add constraint products_categories_fk_category
    foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_product
    foreign key (product_id) references products;

-- PRODUCTS IN BUCKET
DROP TABLE IF EXISTS buckets_products CASCADE;
create table buckets_products (
                                 bucket_id int8 not null,
                                 product_id int8 not null
);

alter table if exists buckets_products
    add constraint buckets_products_fk_product
    foreign key (product_id) references products;

alter table if exists buckets_products
    add constraint buckets_products_fk_bucket
    foreign key (bucket_id) references buckets;

-- ORDERS
DROP SEQUENCE IF EXISTS order_seq;
create sequence order_seq start 1 increment 1;

DROP TABLE IF EXISTS orders CASCADE;

create table orders (
                        id int8 not null,
                        created timestamp,
                        updated timestamp,
                        sum numeric(19, 2),
                        user_id int8,
                        primary key (id)
);

alter table if exists orders
    add constraint orders_fk_user
    foreign key (user_id) references users;


-- DETAILS OF ORDER
DROP TABLE IF EXISTS order_details_seq CASCADE;
create sequence order_details_seq start 1 increment 1;

DROP TABLE IF EXISTS orders_details CASCADE;

create table orders_details (
                                id int8 not null,
                                order_id int8 not null,
                                product_id int8 not null,
                                amount numeric(19, 2),
                                price numeric(19, 2),
                                details_id int8 /*not null*/,
                                primary key (id)
);

alter table if exists orders_details
    add constraint orders_details_fk_order
        foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_product
        foreign key (product_id) references products;