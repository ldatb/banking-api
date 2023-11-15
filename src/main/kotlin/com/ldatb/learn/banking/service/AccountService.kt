package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {
    fun checkIfAccountExists(accountNumber: Long): Boolean =
        accountRepository.existsAccountByAccountNumber(accountNumber)

    fun findByAccountNumber(accountNumber: Long): Account? =
        accountRepository.findAccountByAccountNumber(accountNumber)
}