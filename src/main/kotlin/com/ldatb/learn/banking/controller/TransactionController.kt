package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.service.TransactionService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transaction")
class TransactionController(private val transactionService: TransactionService)
