package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.domain.request.CreateAccountRequestDomain
import com.ldatb.learn.banking.domain.request.UpdateAccountRequestDomain
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Provide tests for the [AccountService]
 * @see [AccountService]
 */
class AccountServiceTest {
    /**
     * Declare all constructors here as a test class can't have more than one
     * primary constructor
     */
    private val accountRepository: AccountRepository = mockk<AccountRepository>()
    private val accountService: AccountService = AccountService(accountRepository)
    private var mockkAccount: Account = Account(
        login = "mock-login",
        hashedPassword = "mock-password",
        firstName = "Mock",
        lastName = "Account"
    )

    /**
     * Verify that the [AccountService.createAccount]
     * is able to create an [Account] based on a [CreateAccountRequestDomain]
     */
    @Test
    fun testCreateAccount() {
        // given
        // need to set these to any() as the Account entity creation generates random
        // values that would cause this test to always fail
        every { accountRepository.findAccountByTransferKey(any()) } returns null
        every { accountRepository.save(any()) } returns mockkAccount

        // when
        val createAccountMock = CreateAccountRequestDomain(
            login = mockkAccount.login,
            password = mockkAccount.hashedPassword,
            firstName = mockkAccount.firstName,
            lastName = mockkAccount.lastName
        )
        val result = accountService.createAccount(createAccountMock)

        // then
        assertEquals(mockkAccount, result)
    }

    /**
     * Verify that the [AccountService.getAccountByLogin]
     * is able to fetch an [Account] by the [Account.login]
     */
    @Test
    fun testGetAccountByLogin() {
        // given
        every { accountRepository.findAccountByLogin("mock-login") } returns mockkAccount

        // when
        val result = accountService.getAccountByLogin("mock-login")

        // then
        assertEquals(mockkAccount, result)
    }

    /**
     * Verify that the [AccountService.getAccountFromTransferKey]
     * is able to fetch an [Account] by the [Account.transferKey]
     */
    @Test
    fun testGetAccountFromTransferKey() {
        // given
        every { accountRepository.findAccountByLogin(mockkAccount.transferKey) } returns mockkAccount

        // when
        val result = accountService.getAccountByLogin(mockkAccount.transferKey)

        // then
        assertEquals(mockkAccount, result)
    }

    /**
     * Verify that the [AccountService.updateAccountFromLogin]
     * is able to update an [Account] based on a [UpdateAccountRequestDomain]
     */
    @Test
    fun testUpdateAccountFromLogin() {
        // given
        every { accountRepository.findAccountByTransferKey(any()) } returns null
        every { accountRepository.save(any()) } returns mockkAccount
        every { accountRepository.findAccountByLogin(any()) } returns mockkAccount

        // when
        // Create the account
        val createAccountMock = CreateAccountRequestDomain(
            login = mockkAccount.login,
            password = mockkAccount.hashedPassword,
            firstName = mockkAccount.firstName,
            lastName = mockkAccount.lastName
        )
        val createResult = accountService.createAccount(createAccountMock)

        // Update the Mockk account
        mockkAccount.login = "update-login"
        mockkAccount.transferKey = "update-tfk"
        mockkAccount.hashedPassword = "update-password"
        mockkAccount.secretToken = 123000U
        mockkAccount.firstName = "update fn"
        mockkAccount.lastName = "update ln"

        // Use the new values of the Mockk account to create the request
        val updateAccountMockk = UpdateAccountRequestDomain(
            login = mockkAccount.login,
            transferKey = mockkAccount.transferKey,
            password = mockkAccount.hashedPassword,
            secretToken = mockkAccount.secretToken,
            firstName = mockkAccount.firstName,
            lastName = mockkAccount.lastName
        )

        // Call the service to update the instance
        val updateResult = accountService.updateAccountFromLogin(createResult.login, updateAccountMockk)

        // then
        assertEquals(mockkAccount, updateResult)
    }

    /**
     * Verify that the [AccountService.deleteAccountByLogin]
     * is able to delete an [Account] by the [Account.login]
     */
    @Test
    fun testDeleteAccountByLogin() {
        // given
        every { accountRepository.deleteAccountByLogin(mockkAccount.login) } returns true

        // when
        val result = accountService.deleteAccountByLogin(mockkAccount.login)

        // then
        assertEquals(true, result)
    }
}
