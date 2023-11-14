package com.ldatb.learn.banking.model

import jakarta.persistence.*

@Entity
@Table(name = "admins")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val username: String,
    var hashedPassword: String
)