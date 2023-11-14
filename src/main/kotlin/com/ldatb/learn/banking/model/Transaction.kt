package com.ldatb.learn.banking.model

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val amount: Long,
    val currency: String = "usd",
    val senderAccountNumber: Long,
    val recipientAccountNumber: Long,
    val timestamp: Timestamp,
)