INSERT INTO users (id, archive, name, lastname, firstname, password, role, bucket_id)
VALUES (1, false, 'manikala', 'valera', 'manikala', 'manikala', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;