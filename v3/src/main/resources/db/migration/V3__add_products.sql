INSERT INTO products (id, vendor, name, price, amount, description)
VALUES (1, 1111, 'Milk', 90.0, 10, 'Dairy product'),
       (2, 2222, 'Beer', 90.0, 10, 'Alcohol product'),
       (3, 3333, 'Bread', 90.0, 10, 'Flour product'),
       (4, 4444, 'Cheese', 90.0, 10, 'Dairy product'),
       (5, 5555, 'Apple', 90.0, 10, 'Fruit');

ALTER SEQUENCE product_seq RESTART WITH 6;