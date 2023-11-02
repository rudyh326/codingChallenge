package com.example.codingchallenge.utils

import android.os.Parcelable
import com.example.codingchallenge.domain.Repo
import kotlinx.parcelize.Parcelize

fun Repo.toParcelable(): ParcelableRepo {
    return ParcelableRepo(
        id,
        name,
        createdAt,
        stargazersCount,
        avatarUrl
    )
}

@Parcelize
data class ParcelableRepo(
    val id : Int?,
    val name : String?,
    val createdAt : String?,
    val stargazersCount : String?,
    val avatarUrl : String?) : Parcelable