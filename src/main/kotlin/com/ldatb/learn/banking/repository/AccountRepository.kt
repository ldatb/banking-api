package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

/**
 * Implements the repository functions for the [Account] entity.
 * Extends a [JpaRepository] interface
 */
interface AccountRepository : JpaRepository<Account, Long> {
    /**
     * Finds an [UserDetails] entity that
     * is extended by the [Account]
     *
     * @param login a String equivalent to [Account.login]
     *
     * @return an [UserDetails] entity
     */
    fun findByLogin(login: String): UserDetails

    /**
     * Finds an [Account] based on its [Account.login]
     *
     * @param login a String equivalent to [Account.login]
     *
     * @return an [Account] entity
     */
    fun findAccountByLogin(login: String): Account?

    /**
     * Finds an [Account] based on its [Account.transferKey]
     *
     * @param transferKey a String equivalent to [Account.transferKey]
     *
     * @return an [Account] entity
     */
    fun findAccountByTransferKey(transferKey: String): Account?

    /**
     * Delete an [Account] based on its [Account.login]
     *
     * @param login a String equivalent to [Account.login]
     *
     * @return a Boolean with whether the account was deleted
     */
    fun deleteAccountByLogin(login: String): Boolean
}
