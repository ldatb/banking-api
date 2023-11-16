package com.ldatb.learn.banking.model

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    val transactionId: Long,

    @Column(name = "amount", nullable = false)
    val amount: Long,

    @Column(name = "currency", nullable = false)
    val currency: String = "usd",

    @Column(name = "sender_transfer_key")
    val senderTransferKey: String,

    @Column(name = "sender_transfer_key")
    val recipientTransferKey: String,

    @Column(name = "timestamp")
    val timestamp: Timestamp = Timestamp.from(Instant.now()),
)