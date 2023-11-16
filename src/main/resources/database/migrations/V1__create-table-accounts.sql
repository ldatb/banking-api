CREATE TABLE IF NOT EXISTS accounts
(
    id               bigint PRIMARY KEY  NOT NULL,
    login            varchar(255) UNIQUE NOT NULL,
    hashed_password  char(64)            NOT NULL,
    transfer_key     char(36) UNIQUE     NOT NULL,
    secret_token     int                 NOT NULL,
    first_name       varchar(16)         NOT NULL,
    last_name        varchar(32)         NOT NULL,
    balance          bigint              NOT NULL DEFAULT 0,
    default_currency char(3)             NOT NULL DEFAULT 'usd',
    role             varchar(255)        NOT NULL,
    updated_at       timestamp           NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP,
    created_at       timestamp           NOT NULL DEFAULT (now())
);