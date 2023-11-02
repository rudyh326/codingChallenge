package com.example.codingchallenge.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codingchallenge.domain.Repo

@Entity
data class DatabaseRepo constructor(
    @PrimaryKey
    val id : Int?,
    val name : String?,
    val createdAt : String?,
    val stargazersCount : String?,
    val avatarUrl : String?)

fun List<DatabaseRepo>.asDomainModel(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            createdAt = it.createdAt,
            stargazersCount = it.stargazersCount,
            avatarUrl = it.avatarUrl
        )
    }
}

