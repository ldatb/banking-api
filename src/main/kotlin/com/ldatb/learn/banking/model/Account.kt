package com.ldatb.learn.banking.model

import com.ldatb.learn.banking.util.generateRandomSecretToken
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "accounts")
data class Account (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val accountNumber: Long,
    var firstName: String,
    var lastName: String,
    var hashedPassword: String,
    var balance: Long = 0,
    var defaultCurrency: String = "usd",
    var secretToken: UInt = generateRandomSecretToken(),
    var updatedAt: Timestamp,
    val createdAt: Timestamp,
) {
    @ManyToMany(mappedBy = "transactions")
    var transactions: List<Transaction> = listOf()
}