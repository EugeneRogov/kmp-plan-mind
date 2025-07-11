package com.eugenerogov.planmind.domain.entities.profile

import org.jetbrains.exposed.sql.Table


object UserProfileTable : Table("user_profiles") {
    val id = varchar("id", 64)
    val name = varchar("name", 255)
    val email = varchar("email", 255).uniqueIndex()
    val avatar = varchar("avatar", 255).nullable()
    val createdAt = varchar("created_at", 64)
    val preferences = text("preferences")

    override val primaryKey = PrimaryKey(id)
}
