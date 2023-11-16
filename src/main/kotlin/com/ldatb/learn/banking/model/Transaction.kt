package com.ldatb.learn.banking.model

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @Column(name = "amount", nullable = false)
    var amount: Long,

    @Column(name = "currency", nullable = false)
    var currency: String = "usd",

    @Column(name = "sender_transfer_key")
    var senderTransferKey: String,

    @Column(name = "sender_transfer_key")
    var recipientTransferKey: String,

    @Column(name = "timestamp")
    var timestamp: Timestamp = Timestamp.from(Instant.now()),
)