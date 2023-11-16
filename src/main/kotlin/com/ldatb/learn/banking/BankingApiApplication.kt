package com.ldatb.learn.banking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.ldatb.learn.banking"])
class BankingAPIApplication

fun main(args: Array<String>) {
    runApplication<BankingAPIApplication>(*args)
}
