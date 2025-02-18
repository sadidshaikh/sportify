package com.example.sportify.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "teams")
class Team(
    @Column(name = "team_id", nullable = false, unique = true)
    val teamId: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "abbreviation", nullable = true)
    val abbreviation: String? = null,

    @Column(name = "display_name", nullable = true)
    val displayName: String? = null,

    @Column(name = "location", nullable = true)
    val location: String? = null,

    @Column(name = "logo", nullable = true)
    val logo: String? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
}