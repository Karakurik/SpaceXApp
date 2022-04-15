package ru.itis.karakurik.spacexapp.data.network.mapper

import ru.itis.karakurik.spacexapp.data.network.response.LaunchResponse
import ru.itis.karakurik.spacexapp.data.network.response.LaunchesListResponse
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import java.util.stream.Collectors
import javax.inject.Inject

class LaunchMapper @Inject constructor() {
    fun map(launchResponse: LaunchResponse): Launch = Launch(
        id = launchResponse.id,
        name = launchResponse.name,
        flightNumber = launchResponse.flightNumber,
        dateUtc = launchResponse.dateUtc,
        dateUnix = launchResponse.dateUnix,
        smallImageUrl = launchResponse.links.patch.small,
        largeImageUrl = launchResponse.links.patch.large,
        webCastUrl = launchResponse.links.webcast,
        articleUrl = launchResponse.links.article,
        wikipedia = launchResponse.links.wikipedia,
        rocket = launchResponse.rocket,
        success = launchResponse.success,
        details = launchResponse.details
    )

    fun map(launchesListResponse: LaunchesListResponse): List<Launch> =
        launchesListResponse.stream().map {
            map(it)
        }.collect(Collectors.toList())
}
