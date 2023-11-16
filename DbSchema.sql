CREATE TABLE IF NOT EXISTS accounts
(
    id               bigint PRIMARY KEY NOT NULL,
    transfer_key     char(36)           NOT NULL,
    password         char(64)           NOT NULL,
    secret_token     int                NOT NULL,
    first_name       varchar(16)        NOT NULL,
    last_name        varchar(32)        NOT NULL,
    balance          bigint             NOT NULL DEFAULT 0,
    default_currency char(3)            NOT NULL DEFAULT 'usd',
    updated_at       timestamp          NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP,
    created_at       timestamp          NOT NULL DEFAULT (now())
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                     bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    amount                 bigint             NOT NULL,
    currency               char(3)            NOT NULL DEFAULT 'usd',
    sender_transfer_key    char(36)           NOT NULL,
    recipient_transfer_key char(36)           NOT NULL,
    timestamp              timestamp          NOT NULL
);

CREATE TABLE IF NOT EXISTS admins
(
    id       bigint PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    username varchar(255) UNIQUE NOT NULL,
    password char(64)            NOT NULL
);

ALTER TABLE transactions
    ADD FOREIGN KEY (senderAccountNumber) REFERENCES accounts (accountNumber);
ALTER TABLE transactions
    ADD FOREIGN KEY (recipientAccountNumber) REFERENCES accounts (accountNumber);