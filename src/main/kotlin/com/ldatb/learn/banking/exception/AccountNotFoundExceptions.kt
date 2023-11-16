package com.ldatb.learn.banking.exception

import org.springframework.http.HttpStatus

class AccountNotFoundWithLoginException(login: String) :
    CustomException("Account with login $login could not be found.", HttpStatus.NOT_FOUND)