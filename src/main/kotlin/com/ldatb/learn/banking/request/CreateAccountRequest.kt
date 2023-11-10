package com.ldatb.learn.banking.request

import com.ldatb.learn.banking.model.Account

data class CreateAccountRequest(
    val accountNumber: String,
    val balance: Long = 0
) {
    fun toAccount(): Account {
        return Account(
            accountNumber = this.accountNumber,
            balance = this.balance,
        )
    }
}