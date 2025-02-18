package com.example.sportify.populate

import com.example.sportify.RestRequestHelper
import com.example.sportify.model.Team
import com.example.sportify.repository.TeamRepository
import com.example.sportify.utils.footballTeams
import com.example.sportify.viewmodels.TeamsResponseViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service

@Service
class PopulateFootball(
    objectMapper: ObjectMapper,
    restTemplateBuilder: RestTemplateBuilder,
    private val teamRepository: TeamRepository,
) : RestRequestHelper(objectMapper, restTemplateBuilder) {

    @Value("\${app.espn.url}")
    private lateinit var espnUrl: String

    fun populateTeams() {
        val leagueTeams = footballTeams.map { fetchTeams(it) }
        val teams = leagueTeams
            .flatMap { it.sports.first().leagues.first().teams }
            .map { teamObject -> teamObject.team }
            .map { teamViewModel ->
                Team(
                    teamId = teamViewModel.id,
                    name = teamViewModel.name,
                    abbreviation = teamViewModel.abbreviation,
                    displayName = teamViewModel.displayName,
                    location = teamViewModel.location,
                    logo = teamViewModel.logos?.firstOrNull()?.href
                )
            }
        teamRepository.saveAll(teams)
    }

    fun fetchTeams(leagueUrl: String): TeamsResponseViewModel {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        return get("$espnUrl/$leagueUrl", headers)
    }
}