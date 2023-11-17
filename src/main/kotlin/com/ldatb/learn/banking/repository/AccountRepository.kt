package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByLogin(login: String): UserDetails
    fun findAccountByLogin(login: String): Account?
    fun findAccountByTransferKey(transferKey: String): Account?
}
