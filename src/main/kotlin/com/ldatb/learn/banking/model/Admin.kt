package com.ldatb.learn.banking.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "admins")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @NotNull
    val username: String,
    @NotNull
    var hashedPassword: String
)