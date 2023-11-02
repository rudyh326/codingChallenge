package com.example.codingchallenge.network

import com.example.codingchallenge.database.DatabaseRepo
import com.example.codingchallenge.domain.Repo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkRepo(
    val id : Int?,
    val name : String?,
    @Json(name = "created_at") val createdAt : String?,
    val owner: NetworkRepoOwner,
    @Json(name = "stargazers_count") val stargazersCount : String?,
)

@JsonClass(generateAdapter = true)
data class NetworkRepoOwner(
    val avatar_url : String?
)

fun List<NetworkRepo>.asDomainModel(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            createdAt = it.createdAt,
            stargazersCount = it.stargazersCount,
            avatarUrl = it.owner.avatar_url
        )
    }
}

fun List<NetworkRepo>.asDatabaseModel(): Array<DatabaseRepo> {
    return map {
        DatabaseRepo(
            id = it.id,
            name = it.name,
            createdAt = it.createdAt,
            stargazersCount = it.stargazersCount,
            avatarUrl = it.owner.avatar_url)
    }.toTypedArray()
}