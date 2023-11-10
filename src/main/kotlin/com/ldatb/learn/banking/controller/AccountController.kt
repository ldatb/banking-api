package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.request.CreateAccountRequest
import com.ldatb.learn.banking.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(private val accountService: AccountService) {
    @GetMapping
    fun getAccounts(): ResponseEntity<List<Account>> =
        ResponseEntity.ok(accountService.getAllAccounts())

    @GetMapping("{id}")
    fun getAccountByID(@PathVariable id: Long): ResponseEntity<Account> =
        ResponseEntity.ok(accountService.getAccountByID(id))

    @PostMapping
    fun createAccount(
        @RequestBody
        createAccountRequest: CreateAccountRequest
    ): ResponseEntity<Account> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(accountService.createAccount(createAccountRequest))
    }
}