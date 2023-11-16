package com.ldatb.learn.banking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication()
class BankingAPIApplication

fun main(args: Array<String>) {
    runApplication<BankingAPIApplication>(*args)
}
