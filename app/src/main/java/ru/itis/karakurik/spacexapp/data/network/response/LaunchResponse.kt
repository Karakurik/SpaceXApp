package ru.itis.karakurik.spacexapp.data.network.response


import com.google.gson.annotations.SerializedName
import java.util.*

data class LaunchResponse(
    @SerializedName("links")
    val links: Links,
    @SerializedName("rocket")
    val rocket: String? = null,
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("details")
    val details: String? = null,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("date_utc")
    val dateUtc: String,
    @SerializedName("date_unix")
    val dateUnix: Long,
    @SerializedName("id")
    val id: String
)
