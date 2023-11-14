package com.ldatb.learn.banking.exception

import org.springframework.http.HttpStatus

class AccountNotFoundException(accountNumber: Long) :
        CustomException("Account with number $accountNumber could not be found.", HttpStatus.NOT_FOUND)