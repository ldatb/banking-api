package com.ldatb.learn.banking.model

import com.ldatb.learn.banking.dto.response.AccountResponseDTO
import com.ldatb.learn.banking.dto.response.AccountResponseDetailsDTO
import com.ldatb.learn.banking.util.generateRandomSecretToken
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "accounts")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0L,

    @Column(name = "login")
    val login: String,

    @Column(name = "hashed_password", nullable = false)
    val hashedPassword: String,

    @Column(name = "transfer_key", length = 36, nullable = false)
    var transferKey: String = UUID.randomUUID().toString(),

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
    val role: AccountRoles = AccountRoles.USER,

    @Column(name = "updated_at")
    val updatedAt: Timestamp = Timestamp.from(Instant.now()),

    @Column(name = "created_at")
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
) : UserDetails {
    fun toAccountResponseDTO(): AccountResponseDTO =
        AccountResponseDTO(
            data = AccountResponseDetailsDTO(
                login = this.login,
                transferKey = this.transferKey,
                secretToken = this.secretToken,
                firstName = this.firstName,
                lastName = this.lastName,
                balance = this.balance,
                defaultCurrency = this.defaultCurrency
            )
        )


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (this.role == AccountRoles.ADMIN) {
            return mutableListOf(
                SimpleGrantedAuthority(AccountRoles.ADMIN.toString()),
                SimpleGrantedAuthority(AccountRoles.USER.toString())
            )
        }
        return mutableListOf(SimpleGrantedAuthority(AccountRoles.USER.toString()))
    }

    override fun getPassword(): String = this.hashedPassword

    override fun getUsername(): String = this.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}