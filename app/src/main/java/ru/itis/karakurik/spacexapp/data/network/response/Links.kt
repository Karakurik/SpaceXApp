package ru.itis.karakurik.spacexapp.data.network.response


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("patch")
    val patch: Patch,
    @SerializedName("webcast")
    val webcast: String? = null,
    @SerializedName("article")
    val article: String? = null,
    @SerializedName("wikipedia")
    val wikipedia: String? = null
)
