package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.controller.AccountController
import com.ldatb.learn.banking.domain.request.CreateAccountRequestDomain
import com.ldatb.learn.banking.domain.request.UpdateAccountRequestDomain
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.reflect.full.memberProperties

/**
 * Implements CRUD methods for the [Account] entity by leveraging the [AccountRepository] repository
 *
 * @param accountRepository is a [AccountRepository] instance, mapped to the [Account] entity.
 */
@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    /**
     * Creates a new [Account] and insert it into the database
     *
     * @param data is a [CreateAccountRequestDomain] request model
     * @return an [Account] entity
     */
    fun createAccount(data: CreateAccountRequestDomain): Account {
        // Create new [Account] instance based on the given data
        var newAccount = Account(
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
        return accountRepository.save(newAccount)
    }

    /**
     * Fetches an [Account] based on the [Account.login] parameter
     *
     * @param login is a String corresponding to [Account.login]
     * @return an optional [Account] entity
     */
    fun getAccountByLogin(login: String): Account? = accountRepository.findAccountByLogin(login)

    /**
     * Fetches an [Account] based on the [transferKey] given by the
     * [AccountController]
     *
     * @param transferKey the [Account.transferKey] value
     * @return an optional [Account] entity
     */
    fun getAccountFromTransferKey(transferKey: String): Account? =
        accountRepository.findAccountByTransferKey(transferKey)

    /**
     * Updates an [Account] based on an [UpdateAccountRequestDomain] request
     *
     * @param login the [Account.login] value
     * @param valuesToUpdate an [UpdateAccountRequestDomain] request
     *
     * @return a non-nullable [Account] entity
     */
    fun updateAccountFromLogin(login: String, valuesToUpdate: UpdateAccountRequestDomain): Account {
        // Find the account
        // Not nullable since this was already verified by the [AccountController]
        var account = getAccountByLogin(login)!!

        // Update every non-null field
        for (property in UpdateAccountRequestDomain::class.memberProperties) {
            if (property.get(valuesToUpdate) != null) {
                when (property.name) {
                    "login" -> account.login = valuesToUpdate.login!!
                    "transferKey" -> account.transferKey = valuesToUpdate.transferKey!!
                    "password" -> account.hashedPassword = BCryptPasswordEncoder().encode(valuesToUpdate.password!!)
                    "secretToken" -> account.secretToken = valuesToUpdate.secretToken!!
                    "firstName" -> account.firstName = valuesToUpdate.firstName!!
                    "lastName" -> account.lastName = valuesToUpdate.lastName!!
                }
            }
        }

        // All values update, so save the account
        return accountRepository.save(account)
    }

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
