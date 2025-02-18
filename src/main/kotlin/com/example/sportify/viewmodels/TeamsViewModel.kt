package com.example.sportify.viewmodels

data class TeamLogo(
    val href: String?,
)

data class TeamViewModel(
    val id: String,
    val abbreviation: String?,
    val displayName: String?,
    val name: String,
    val location: String?,
    val logos: List<TeamLogo>?,
)

data class TeamObject(
    val team: TeamViewModel,
)

data class LeagueTeams(
    val teams: List<TeamObject>,
)

data class SportsTeams(
    val leagues: List<LeagueTeams>,
)

data class TeamsResponseViewModel(
    val sports: List<SportsTeams>,
)