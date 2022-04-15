package ru.itis.karakurik.spacexapp.data.network.repository

import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import it.czerwinski.android.hilt.annotations.BoundTo
import ru.itis.karakurik.spacexapp.data.network.SpaceXApi
import ru.itis.karakurik.spacexapp.data.network.mapper.LaunchMapper
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject

@BoundTo(supertype = SpaceXRepository::class, component = SingletonComponent::class)
class SpaceXRepositoryImpl @Inject constructor(
    private val spaceXApi: SpaceXApi,
    private val launchMapper: LaunchMapper,
) : SpaceXRepository {

    override fun getLaunches(): Single<List<Launch>> =
        spaceXApi.getLaunches()
            .map {
                launchMapper.map(it)
            }

    override fun getLaunch(launchId: String): Single<Launch> =
        spaceXApi.getLaunch(launchId).map {
            launchMapper.map(it)
        }

    override fun getLatestLaunch(): Single<Launch> =
        spaceXApi.getLatestLaunch().map {
            launchMapper.map(it)
        }

    override fun getPastLaunches(): Single<List<Launch>> =
        spaceXApi.getPastLaunches().map {
            launchMapper.map(it)
        }

    override fun getUpcomingLaunches(): Single<List<Launch>> =
        spaceXApi.getUpcomingLaunches().map {
            launchMapper.map(it)
        }
}
