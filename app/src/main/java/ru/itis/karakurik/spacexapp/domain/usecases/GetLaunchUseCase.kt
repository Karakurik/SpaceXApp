package ru.itis.karakurik.spacexapp.domain.usecases

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLaunchUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    operator fun invoke(launchId: String): Single<Launch> =
        spaceXRepository.getLaunch(launchId)
            .subscribeOn(Schedulers.io())
}
