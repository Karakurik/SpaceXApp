package ru.itis.karakurik.spacexapp.domain.usecases

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLatestLaunchUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    operator fun invoke() : Single<Launch> =
        spaceXRepository.getLatestLaunch()
            .subscribeOn(Schedulers.io())
}
