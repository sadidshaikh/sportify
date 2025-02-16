package com.example.sportify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SportifyApplication

fun main(args: Array<String>) {
    runApplication<SportifyApplication>(*args)
}
