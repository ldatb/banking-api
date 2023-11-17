package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.dto.request.CreateAccountRequestDTO
import com.ldatb.learn.banking.exception.AccountAlreadyExistsException
import com.ldatb.learn.banking.exception.AccountNotFoundException
import com.ldatb.learn.banking.service.AccountService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(
    private val accountService: AccountService
) {
    @PostMapping()
    @Transactional
    fun registerAccount(@RequestBody data: CreateAccountRequestDTO): ResponseEntity<Any> {
        if (accountService.getAccountByLogin(data.login) != null) {
            return ResponseEntity.badRequest().body(
                AccountAlreadyExistsException(
                    message = "Unable to create account with login ${data.login}",
                    details = "${data.login} is not a valid login"
                )
            )
        }

        val newAccount = accountService.createAccount(data)
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount.toAccountResponseDTO())
    }

    @GetMapping()
    fun getAccount(request: HttpServletRequest): ResponseEntity<Any> {
        val account = accountService.getAccountFromRequest(request)
        if (account == null) {
            val login = accountService.getAccountLoginFromRequest(request)
            return ResponseEntity.badRequest().body(
                AccountNotFoundException(
                    message = "Account with login $login not found",
                    details = "Bearer token corresponds to an unknown account of login $login"
                )
            )
        }
        return ResponseEntity.ok(account.toAccountResponseDTO())
    }
}
