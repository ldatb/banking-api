package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.model.Transaction
import com.ldatb.learn.banking.service.AdminService
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController(private val adminService: AdminService) {
    // LOGIN AND LOGOUT
    @PostMapping("login")
    fun loginToAdminAccount(): ResponseEntity<Admin> {
        TODO()
    }

    @DeleteMapping("login")
    fun logoutOfAdminAccount(): ResponseEntity<Admin> {
        TODO()
    }

    // ACCOUNT
    @GetMapping("accounts")
    fun getAllAccounts(): ResponseEntity<List<Account>> {
        TODO()
    }

    @GetMapping("accounts/{accountNumber}")
    fun getAccountByNumber(@PathVariable accountNumber: Long): ResponseEntity<Account> {
        TODO()
    }

    // TRANSACTIONS
    @GetMapping("transactions")
    fun getAllTransactions(): ResponseEntity<List<Transaction>> {
        TODO()
    }

    @GetMapping("transactions/{id}")
    fun getTransactionByID(@PathVariable id: Long): ResponseEntity<Transaction> {
        TODO()
    }

    @GetMapping("transactions/{accountNumber}")
    fun getAccountTransactions(@PathVariable accountNumber: Long): ResponseEntity<List<Transaction>> {
        TODO()
    }

    @DeleteMapping("transactions/{id}")
    fun deleteTransaction(@PathVariable id: Long): ResponseEntity<Transaction> {
        TODO()
    }
}