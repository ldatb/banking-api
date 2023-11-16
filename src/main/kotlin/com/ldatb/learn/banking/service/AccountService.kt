package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.dto.request.CreateAccountDTO
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {
    // CREATE
    fun createAccount(data: CreateAccountDTO): Account {
        val newAccount = Account(
            login = data.login,
            hashedPassword = BCryptPasswordEncoder().encode(data.password),
            firstName = data.firstName,
            lastName = data.lastName
        )
        accountRepository.save(newAccount)
        return newAccount
    }

    // READ
    // UPDATE
    // DELETE
}