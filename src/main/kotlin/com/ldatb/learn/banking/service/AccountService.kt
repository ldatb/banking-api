package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.controller.AccountController
import com.ldatb.learn.banking.dto.request.CreateAccountRequestDTO
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.util.getTokenFromRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Implements CRUD methods for the [Account] entity by leveraging the [AccountRepository] repository
 *
 * @param accountRepository is a [AccountRepository] instance, mapped to the [Account] entity.
 * @param tokenService is [TokenService] instance, responsible for getting [Account] entities from the request
 */
@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val tokenService: TokenService
) {
    // CREATE

    /**
     * Creates a new [Account] and insert it into the database
     *
     * @param data is a [CreateAccountRequestDTO] request model
     * @return an [Account] entity
     */
    fun createAccount(data: CreateAccountRequestDTO): Account {
        // Create new [Account] instance based on the given data
        val newAccount = Account(
            login = data.login,
            hashedPassword = BCryptPasswordEncoder().encode(data.password),
            firstName = data.firstName,
            lastName = data.lastName
        )

        // Make sure the UUID for the new account is not used by another one
        while (accountRepository.findAccountByTransferKey(newAccount.transferKey) != null) {
            newAccount.transferKey = UUID.randomUUID().toString()
        }

        // Save the new [Account] into the database
        accountRepository.save(newAccount)
        return newAccount
    }

    // READ

    /**
     * Fetches an [Account] based on the [Account.login] parameter
     *
     * @param login is a String corresponding to [Account.login]
     * @return an optional [Account] entity
     */
    fun getAccountByLogin(login: String): Account? = accountRepository.findAccountByLogin(login)

    /**
     * Fetches an [Account.login] based on the [request] given by
     * the [AccountController]
     *
     * @param request the [request] values
     * @return a String containing the [Account.login]
     */
    fun getAccountLoginFromRequest(request: HttpServletRequest): String =
        tokenService.validateToken(getTokenFromRequest(request))

    /**
     * Fetches an [Account] based on the [request] given by the
     * [AccountController]
     *
     * @param request the [request] values
     * @return an optional [Account] entity
     */
    fun getAccountFromRequest(request: HttpServletRequest): Account? =
        accountRepository.findAccountByLogin(getAccountLoginFromRequest(request))

    /**
     * Fetches an [Account] based on the [transferKey] given by the
     * [AccountController]
     *
     * @param transferKey the [Account.transferKey] value
     * @return an optional [Account] entity
     */
    fun getAccountFromTransferKey(transferKey: String): Account? =
        accountRepository.findAccountByTransferKey(transferKey)

    // UPDATE
    // DELETE

    /**
     * Deletes an [Account] based on the [login] given by the
     * [AccountController]
     *
     * @param login the [Account.login] value
     * @return a Boolean with whether the account was deleted
     */
    fun deleteAccountByLogin(login: String): Boolean =
        accountRepository.deleteAccountByLogin(login)
}
