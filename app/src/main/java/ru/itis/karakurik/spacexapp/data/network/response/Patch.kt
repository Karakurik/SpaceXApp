package ru.itis.karakurik.spacexapp.data.network.response


import com.google.gson.annotations.SerializedName

data class Patch(
    @SerializedName("small")
    val small: String? = null,
    @SerializedName("large")
    val large: String? = null
)
