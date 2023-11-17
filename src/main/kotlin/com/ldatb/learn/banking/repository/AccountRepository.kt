package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

/**
 * Implements the repository functions for the [com.ldatb.learn.banking.model.Account] entity.
 * Extends a [org.springframework.data.jpa.repository.JpaRepository] interface
 */
interface AccountRepository : JpaRepository<Account, Long> {
    /**
     * Finds an [org.springframework.security.core.userdetails.UserDetails] entity that
     * is extended by the [com.ldatb.learn.banking.model.Account]
     *
     * @param login a String equivalent to [com.ldatb.learn.banking.model.Account.login]
     *
     * @return an [org.springframework.security.core.userdetails.UserDetails] entity
     */
    fun findByLogin(login: String): UserDetails

    /**
     * Finds an [com.ldatb.learn.banking.model.Account] based on its login
     *
     * @param login a String equivalent to [com.ldatb.learn.banking.model.Account.login]
     *
     * @return an [com.ldatb.learn.banking.model.Account] entity
     */
    fun findAccountByLogin(login: String): Account?

    /**
     * Finds an [com.ldatb.learn.banking.model.Account] based on its transferKey
     *
     * @param transferKey a String equivalent to [com.ldatb.learn.banking.model.Account.transferKey]
     *
     * @return an [com.ldatb.learn.banking.model.Account] entity
     */
    fun findAccountByTransferKey(transferKey: String): Account?
}
