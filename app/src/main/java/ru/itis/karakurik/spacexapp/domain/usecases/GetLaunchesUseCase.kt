package ru.itis.karakurik.spacexapp.domain.usecases

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLaunchesUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    operator fun invoke() : Single<List<Launch>> =
        spaceXRepository.getLaunches()
            .subscribeOn(Schedulers.io())
}
