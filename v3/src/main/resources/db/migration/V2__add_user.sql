INSERT INTO users (id, username, lastname, firstname, password, archive, role, bucket_id)
VALUES (1, 'manikala', 'valera', 'manikala', 'manikala', false, 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;