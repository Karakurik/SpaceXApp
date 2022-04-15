package ru.itis.karakurik.spacexapp.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.itis.karakurik.spacexapp.data.network.response.LaunchesListResponse
import ru.itis.karakurik.spacexapp.domain.entity.Launch

interface SpaceXRepository {
    fun getLaunches(): Single<List<Launch>>
    fun getLaunch(launchId: String): Single<Launch>
    fun getLatestLaunch(): Single<Launch>
    fun getPastLaunches(): Single<List<Launch>>
    fun getUpcomingLaunches(): Single<List<Launch>>
}
