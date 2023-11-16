package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.model.Transaction
import com.ldatb.learn.banking.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transaction")
class TransactionController(private val transactionService: TransactionService)