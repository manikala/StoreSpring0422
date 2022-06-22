-- USERS
create sequence user_seq start 1 increment 1;

create table users (
                       id int8 not null,
                       username varchar(255),
                       lastname varchar(255),
                       firstname varchar(255),
                       password varchar(255),
                       archive boolean not null,
                       role varchar(255),
                       bucket_id int8,
                       primary key (id)
);
-- BUCKET
create sequence bucket_seq start 1 increment 1;

create table buckets (
                         id int8 not null,
                         total_amount float8,
                         user_id int8,
                         primary key (id)
);

-- LINK BETWEEN BUCKET AND USER
alter table if exists buckets -- изменим таблицу если будет наличие строк в
    add constraint buckets_fk_user -- добавляем ограничения на
    foreign key (user_id) references users; -- создание связи по внешнему ключу, и контроль на наличие в точности совпадающего по значению

-- CATEGORY
create sequence category_seq start 1 increment 1;

create table categories (
                            id int8 not null,
                            name varchar(255),
                            primary key (id)
);
-- PRODUCTS
create sequence product_seq start 1 increment 1;

create table products (
                          id int8 not null,
                          vendor int4,
                          name varchar(255),
                          price float8,
                          amount int4,
                          description varchar(255),
                          primary key (id)
);

-- CATEGORY AND PRODUCT
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
create table bucket_products (
                                 bucket_id int8 not null,
                                 product_id int8 not null
);

alter table if exists bucket_products
    add constraint bucket_products_fk_product
    foreign key (product_id) references products;

alter table if exists bucket_products
    add constraint bucket_products_fk_bucket
    foreign key (bucket_id) references buckets;

-- ORDERS
create sequence order_seq start 1 increment 1;

create table orders (
                        id int8 not null,
                        product_id int8 not null,
                        quantity float8,
                        total_amount float8,
                        user_id int8,
                        primary key (id)
);

alter table if exists orders
    add constraint orders_fk_user
    foreign key (user_id) references users;

alter table if exists orders
    add constraint orders_fk_product
    foreign key (product_id) references products;