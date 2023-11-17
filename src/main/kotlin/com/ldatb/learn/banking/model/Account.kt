package com.ldatb.learn.banking.model

import com.ldatb.learn.banking.dto.response.AccountResponseDTO
import com.ldatb.learn.banking.dto.response.AccountResponseDetailsDTO
import com.ldatb.learn.banking.util.generateRandomSecretToken
import jakarta.persistence.* // ktlint-disable no-wildcard-imports
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

/**
 * An Account represents an user in the application. It provides functionalities
 * for both the database and the application's security token.
 *
 * @param id is the ID for this account in the database. Not used by the application
 * @param login is the login that an user needs to provide in order to be authenticated
 * @param hashedPassword is the account's password. Stored after hashing
 * @param transferKey is the key that other users can use to make transactions to this account
 * @param secretToken is a token that the user must provide to make transactions and modify the account
 * @param firstName is the account's owner first name
 * @param lastName is the account's owner last name
 * @param balance is the amount of money that the account has
 * @param defaultCurrency is the default currency used in this account's transactions
 * @param role is only used by the application's security. By default, this will be `USER`
 * @param updatedAt timestamp of when the account was last updated. Only used by the database
 * @param createdAt timestamp of when the account was created. Only used by the database
 */
@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    val createdAt: Timestamp = Timestamp.from(Instant.now())
) : UserDetails {
    /**
     * Casts the information of the Account class to an AccountResponseDTO class, which
     * is the response that the API gives when asked about information on the account of the user
     * @see [AccountResponseDTO] for more information
     * @return [AccountResponseDTO]
     */
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

    /**
     * Used by the application's security service.
     * The authorities imply what a user can do and what it cannot.
     * @see [com.ldatb.learn.banking.config.SecurityConfig.securityFilterChain] for more information
     * @return a collection of the authorities in MutableCollection<out GrantedAuthority> format
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (this.role == AccountRoles.ADMIN) {
            return mutableListOf(
                SimpleGrantedAuthority(AccountRoles.ADMIN.toString()),
                SimpleGrantedAuthority(AccountRoles.USER.toString())
            )
        }
        return mutableListOf(SimpleGrantedAuthority(AccountRoles.USER.toString()))
    }

    /**
     * Used by the application's security service.
     * Get the password of the user
     *
     * @return a String representing the password of the account
     */
    override fun getPassword(): String = this.hashedPassword

    /**
     * Used by the application's security service.
     * Get the username (login parameter) of the user
     *
     * @return a String representing the username of the account
     */
    override fun getUsername(): String = this.id.toString()

    /**
     * Used by the application's security service.
     * The application doesn't make any use of that, so always return true
     *
     * @return a true Boolean
     */
    override fun isAccountNonExpired(): Boolean = true

    /**
     * Used by the application's security service.
     * The application doesn't make any use of that, so always return true
     *
     * @return a true Boolean
     */
    override fun isAccountNonLocked(): Boolean = true

    /**
     * Used by the application's security service.
     * The application doesn't make any use of that, so always return true
     *
     * @return a true Boolean
     */
    override fun isCredentialsNonExpired(): Boolean = true

    /**
     * Used by the application's security service.
     * The application doesn't make any use of that, so always return true
     *
     * @return a true Boolean
     */
    override fun isEnabled(): Boolean = true
}
