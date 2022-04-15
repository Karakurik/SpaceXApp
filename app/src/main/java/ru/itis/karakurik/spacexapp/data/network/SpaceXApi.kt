package ru.itis.karakurik.spacexapp.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.itis.karakurik.spacexapp.data.network.response.LaunchResponse
import ru.itis.karakurik.spacexapp.data.network.response.LaunchesListResponse

interface SpaceXApi {

    @GET("/v4/launches")
    fun getLaunches(): Single<LaunchesListResponse>

    @GET("/v4/launches/{id}")
    fun getLaunch(@Path("id") launchId: String): Single<LaunchResponse>

    @GET("/v4/launches/latest")
    fun getLatestLaunch(): Single<LaunchResponse>

    @GET("/v4/launches/past")
    fun getPastLaunches(): Single<LaunchesListResponse>

    @GET("/v4/launches/upcoming")
    fun getUpcomingLaunches(): Single<LaunchesListResponse>
}
