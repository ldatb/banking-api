package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.model.Transaction
import com.ldatb.learn.banking.service.TransactionService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * The REST controller for the `/transaction` endpoint
 *
 * @param transactionService is the service for the [Transaction] entity
 */
@RestController
@RequestMapping("transaction")
class TransactionController(private val transactionService: TransactionService)
