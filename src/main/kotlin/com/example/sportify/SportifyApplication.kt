package com.example.sportify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SportifyApplication

fun main(args: Array<String>) {
    runApplication<SportifyApplication>(*args)
}
