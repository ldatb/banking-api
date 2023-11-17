package com.ldatb.learn.banking.model

import jakarta.persistence.* // ktlint-disable no-wildcard-imports
import java.sql.Timestamp
import java.time.Instant

/**
 * A Transaction represents the exchange of balance between 2 Accounts.
 *
 * @param id is the ID for this transaction
 * @param amount is the amount of balance that is being exchanged
 * @param currency the currency of this transaction
 * @param senderTransferKey is the [Account.transferKey] of the account that's sending the amounts
 * @param recipientTransferKey is the transfer key [Account.transferKey] of the account that's receiving the amounts
 * @param timestamp timestamp of when the transaction was made
 * @see [Account]
 */
@Entity
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @Column(name = "amount", nullable = false)
    var amount: Long,

    @Column(name = "currency", nullable = false)
    var currency: String = "usd",

    @Column(name = "sender_transfer_key")
    var senderTransferKey: String,

    @Column(name = "recipient_transfer_key")
    var recipientTransferKey: String,

    @Column(name = "timestamp")
    var timestamp: Timestamp = Timestamp.from(Instant.now())
)
