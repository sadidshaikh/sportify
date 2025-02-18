package com.example.sportify.populate

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ScheduledTasks(
    val populateFootball: PopulateFootball,
) {
    @Scheduled(cron = "0 0 0 * * SUN")
    fun populateTeams() {
        populateFootball.populateTeams()
    }
}