package ru.itis.karakurik.spacexapp.domain.entity

import android.telecom.Call

data class Launch (
    val id: String,
    val name: String,
    val flightNumber: Int,
    val dateUtc: String,
    val dateUnix: Long,
    val smallImageUrl: String?,
    val largeImageUrl: String?,
    val webCastUrl: String?,
    val articleUrl: String?,
    val wikipedia: String?,
    val rocket: String?,
    val success: Boolean?,
    val details: String?,

)
