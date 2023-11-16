package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun existsAccountByAccountTransferKey(transferKey: String): Boolean
    fun getAccountByAccountTransferKey(transferKey: String): Account?
}