CREATE TABLE IF NOT EXISTS transactions
(
    id                     bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    amount                 bigint             NOT NULL,
    currency               char(3)            NOT NULL DEFAULT 'usd',
    sender_transfer_key    char(36)           NOT NULL,
    recipient_transfer_key char(36)           NOT NULL,
    timestamp              timestamp          NOT NULL
);

ALTER TABLE transactions
    ADD FOREIGN KEY (senderAccountNumber) REFERENCES accounts (accountNumber);
ALTER TABLE transactions
    ADD FOREIGN KEY (recipientAccountNumber) REFERENCES accounts (accountNumber);