CREATE TABLE `accounts` (
    `account_number` bigint UNIQUE PRIMARY KEY NOT NULL,
    `firstName` string NOT NULL,
    `lastName` string NOT NULL,
    `hashed_password` string NOT NULL,
    `balance` bigint NOT NULL DEFAULT 0,
    `default_currency` string NOT NULL DEFAULT "usd",
    `secret_token` int NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT (now()),
    `created_at` timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE `transactions` (
    `id` string UNIQUE PRIMARY KEY NOT NULL,
    `amount` bigint NOT NULL,
    `currency` string NOT NULL DEFAULT "usd",
    `sender_account_number` bigint NOT NULL,
    `recipient_account_number` bigint NOT NULL,
    `timestamp` timestamp NOT NULL DEFAULT (now())
);

ALTER TABLE `transactions` ADD FOREIGN KEY (`sender_account_number`) REFERENCES `accounts` (`account_number`);
ALTER TABLE `transactions` ADD FOREIGN KEY (`recipient_account_number`) REFERENCES `accounts` (`account_number`);