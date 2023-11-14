package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(private val accountService: AccountService) {
    // GET
    @GetMapping
    fun getAccountByNumber(): ResponseEntity<Account> {
        TODO()
    }

    // LOGIN AND LOGOUT
    @PostMapping("login")
    fun loginToAccount(): ResponseEntity<Account> {
        TODO()
    }

    @DeleteMapping("login")
    fun logoutOfAccount(): ResponseEntity<Account> {
        TODO()
    }

    // UPDATE
    @PutMapping("token")
    fun updateAccountSecretToken(): ResponseEntity<Account> {
        TODO()
    }

    // CREATE AND DELETE
    @PostMapping
    fun createAccount(): ResponseEntity<Account> {
        TODO()
    }

    @DeleteMapping
    fun deleteAccount(): ResponseEntity<Account> {
        TODO()
    }
}