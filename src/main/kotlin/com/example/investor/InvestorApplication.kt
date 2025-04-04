package com.example.investor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InvestorApplication

fun main(args: Array<String>) {
    runApplication<InvestorApplication>(*args)
}
