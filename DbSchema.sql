CREATE TABLE IF NOT EXISTS accounts
(
    accountNumber   bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstName       varchar(16)        NOT NULL,
    lastName        varchar(32)        NOT NULL,
    hashedPassword  char(64)           NOT NULL,
    balance         bigint             NOT NULL DEFAULT 0,
    defaultCurrency char(3)            NOT NULL DEFAULT 'usd',
    secretToken     int                NOT NULL,
    updatedAt       timestamp          NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP,
    createdAt       timestamp          NOT NULL DEFAULT (now())
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                     bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    amount                 bigint             NOT NULL,
    currency               char(3)            NOT NULL DEFAULT 'usd',
    senderAccountNumber    bigint             NOT NULL,
    recipientAccountNumber bigint             NOT NULL,
    timestamp              timestamp          NOT NULL
);

CREATE TABLE IF NOT EXISTS admins
(
    id             int PRIMARY KEY     NOT NULL AUTO_INCREMENT,
    username       varchar(255) UNIQUE NOT NULL,
    hashedPassword char(64)            NOT NULL
);

ALTER TABLE transactions
    ADD FOREIGN KEY (senderAccountNumber) REFERENCES accounts (accountNumber);
ALTER TABLE transactions
    ADD FOREIGN KEY (recipientAccountNumber) REFERENCES accounts (accountNumber);