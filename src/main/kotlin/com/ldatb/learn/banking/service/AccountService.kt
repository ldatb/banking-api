package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.dto.request.CreateAccountRequestDTO
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.util.RequestUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val tokenService: TokenService,
) {
    // CREATE
    fun createAccount(data: CreateAccountRequestDTO): Account {
        val newAccount = Account(
            login = data.login,
            hashedPassword = BCryptPasswordEncoder().encode(data.password),
            firstName = data.firstName,
            lastName = data.lastName
        )

        while (accountRepository.findAccountByTransferKey(newAccount.transferKey) != null) {
            newAccount.transferKey = UUID.randomUUID().toString()
        }

        accountRepository.save(newAccount)
        return newAccount
    }

    // READ
    fun getAccountByLogin(login: String): Account? = accountRepository.findAccountByLogin(login)

    fun getAccountLoginFromRequest(request: HttpServletRequest): String =
        tokenService.validateToken(RequestUtils().getTokenFromRequest(request))

    fun getAccountFromRequest(request: HttpServletRequest): Account? =
        accountRepository.findAccountByLogin(getAccountLoginFromRequest(request))

    // UPDATE
    // DELETE
}
