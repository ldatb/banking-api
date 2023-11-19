package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.dto.request.CreateAccountRequestDTO
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
    private val mockkAccount: Account = Account(
        login = "mock-login",
        hashedPassword = "mock-password",
        firstName = "Mock",
        lastName = "Account",
    )

    /**
     * Verify that the [AccountService.createAccount]
     * is able to create an [Account] based on a [CreateAccountRequestDTO]
     */
    @Test
    fun testCreateAccount() {
        // given
        // need to set these to any() as the Account entity creation generates random
        // values that would cause this test to always fail
        every { accountRepository.findAccountByTransferKey(any()) } returns null
        every { accountRepository.save(any()) } returns mockkAccount
        every { accountRepository.createAccount(any()) } returns mockkAccount

        // when
        val createAccountMock = CreateAccountRequestDTO(
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