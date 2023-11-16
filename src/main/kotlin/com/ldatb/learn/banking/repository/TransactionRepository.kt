package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long>