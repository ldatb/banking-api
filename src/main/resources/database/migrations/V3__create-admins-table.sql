CREATE TABLE IF NOT EXISTS admins
(
    id       bigint PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    username varchar(255) UNIQUE NOT NULL,
    password char(64)            NOT NULL
);