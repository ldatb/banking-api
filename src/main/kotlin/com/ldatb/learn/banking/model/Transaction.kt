package com.ldatb.learn.banking.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.sql.Timestamp

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @NotNull
    val amount: Long,
    val currency: String = "usd",
    @NotNull
    val senderAccountNumber: Long,
    @NotNull
    val recipientAccountNumber: Long,
    val timestamp: Timestamp,
)