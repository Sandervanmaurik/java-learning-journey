DELETE FROM user_roles;
DELETE FROM user;
INSERT IGNORE INTO user (username, password)
VALUES ('user','$2a$12$biS3bBUDslU19NL661A.TeIZ6Hdd2mLQ9tKGdWJFR8r8rvFzIfXPm'),
       ('admin','$2a$12$biS3bBUDslU19NL661A.TeIZ6Hdd2mLQ9tKGdWJFR8r8rvFzIfXPm');

INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_USER' FROM user WHERE username = 'user';
INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_USER' FROM user WHERE username = 'admin';
INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_ADMIN' FROM user WHERE username = 'admin';
