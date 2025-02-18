package com.example.sportify.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "matches")
class Match(
    @Column(name = "first_participant", nullable = true)
    val firstParticipant: String? = null,

    @Column(name = "second_participant", nullable = true)
    val secondParticipant: String? = null,

    @Column(name = "sport", nullable = true)
    val sport: SportType? = SportType.UNDEFINED,

    @Column(name = "scheduled_at", nullable = true)
    val scheduledAt: LocalDateTime? = null,

    @Column(name = "status", nullable = true)
    val status: MatchStatus? = MatchStatus.TO_BE_DECIDED,

    @Column(name = "venue", nullable = true)
    val venue: String? = null,

    @Column(name = "league", nullable = true)
    val league: League? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
}

enum class SportType {
    UNDEFINED,
    FOOTBALL,
    CRICKET,
    TENNIS,
}

enum class MatchStatus {
    TO_BE_DECIDED,
    SCHEDULED,
    COMPLETED,
}

enum class League {
    EPL,
    UCL,
    SERIE_A,
    LA_LIGA,
    BUNDESLIGA,
}
