package com.ldatb.learn.banking.model

import jakarta.persistence.*

@Entity
@Table(name = "admins")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long,

    @Column(name = "username", nullable = false)
    val username: String,

    @Column(name = "password", nullable = false)
    var password: String
)