package com.ldatb.learn.banking.exception

import org.springframework.http.HttpStatus

class AccountNotFoundException(accountLogin: String) :
        CustomException("Account with number $accountLogin could not be found.", HttpStatus.NOT_FOUND)