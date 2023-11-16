package com.ldatb.learn.banking.model

import com.ldatb.learn.banking.util.generateRandomSecretToken
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "accounts")
data class Account (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val accountId: Long,

    @Column(name = "transfer_key", length = 36, nullable = false)
    val accountTransferKey: String = UUID.randomUUID().toString(),

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "secret_token", nullable = false)
    val secretToken: UInt = generateRandomSecretToken(),

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = false)
    val lastName: String,

    @Column(name = "balance", nullable = false)
    val balance: Long = 0L,

    @Column(name = "default_currency")
    val defaultCurrency: String = "usd",

    @Column(name = "role")
    val role: String = "USER",

    @Column(name = "updated_at")
    val updatedAt: Timestamp = Timestamp.from(Instant.now()),

    @Column(name = "created_at")
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
) {
    @ManyToMany(mappedBy = "transactions")
    var transactions: List<Transaction> = listOf()
}