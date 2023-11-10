package com.ldatb.learn.banking.model

import jakarta.persistence.*

@Entity
//@Table(name = "accounts")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    var accountNumber: String,
    var balance: Long,
)
