package com.eugenerogov.planmind.domain.entities.profile

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

data class UserProfileDb(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String?,
    val createdAt: String,
    val preferences: String
)

object UserProfileDao {
    fun findById(id: String): UserProfileDb? = transaction {
        UserProfileTable
            .selectAll()
            .where { UserProfileTable.id eq id }
            .map(::toUserProfileDb)
            .singleOrNull()
    }

    fun findByEmail(email: String): UserProfileDb? = transaction {
        UserProfileTable
            .selectAll()
            .where { UserProfileTable.email eq email }
            .map { toUserProfileDb(it) }
            .singleOrNull()
    }

    fun insert(profile: UserProfileDb) = transaction {
        UserProfileTable.insert {
            it[id] = profile.id
            it[name] = profile.name
            it[email] = profile.email
            it[avatar] = profile.avatar
            it[createdAt] = profile.createdAt
            it[preferences] = profile.preferences
        }
    }

    private fun toUserProfileDb(row: ResultRow) = UserProfileDb(
        id = row[UserProfileTable.id],
        name = row[UserProfileTable.name],
        email = row[UserProfileTable.email],
        avatar = row[UserProfileTable.avatar],
        createdAt = row[UserProfileTable.createdAt],
        preferences = row[UserProfileTable.preferences]
    )
}
