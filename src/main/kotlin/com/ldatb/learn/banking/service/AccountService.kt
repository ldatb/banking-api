package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import com.ldatb.learn.banking.exception.AccountNotFoundException
import com.ldatb.learn.banking.request.CreateAccountRequest
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {
    fun getAllAccounts(): List<Account> = accountRepository.findAll()

    fun getAccountByID(id: Long): Account = accountRepository.findById(id)
        .orElseThrow { AccountNotFoundException("account with ID $id not found!") }

    fun createAccount(createAccountRequest: CreateAccountRequest): Account =
        accountRepository.save(createAccountRequest.toAccount())
}