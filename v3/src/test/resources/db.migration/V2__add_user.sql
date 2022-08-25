INSERT INTO users (id, name, lastname, firstname, password, archive, role)
VALUES (1, 'manikala', 'valera', 'manikala', '$2a$10$76KlYBa4jD2o3ibcou6kXee7BfyT99QHNLh4qbGx3RidKtOR0mtxG', false, 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;