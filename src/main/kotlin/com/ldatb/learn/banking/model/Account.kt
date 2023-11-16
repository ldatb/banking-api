package com.ldatb.learn.banking.model

import com.ldatb.learn.banking.util.generateRandomSecretToken
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "accounts")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,

    @Column(name = "login")
    var login: String,

    @Column(name = "hashed_password", nullable = false)
    var hashedPassword: String,

    @Column(name = "transfer_key", length = 36, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    var transferKey: String,

    @Column(name = "secret_token", nullable = false)
    var secretToken: UInt = generateRandomSecretToken(),

    @Column(name = "first_name", nullable = false)
    var firstName: String,

    @Column(name = "last_name", nullable = false)
    var lastName: String,

    @Column(name = "balance", nullable = false)
    var balance: Long = 0L,

    @Column(name = "default_currency")
    var defaultCurrency: String = "usd",

    @Column(name = "role")
    var role: AccountRoles = AccountRoles.USER,

    @Column(name = "updated_at")
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),

    @Column(name = "created_at")
    var createdAt: Timestamp = Timestamp.from(Instant.now()),
) : UserDetails {
    @ManyToMany(mappedBy = "transactions")
    var transactions: List<Transaction> = listOf()

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