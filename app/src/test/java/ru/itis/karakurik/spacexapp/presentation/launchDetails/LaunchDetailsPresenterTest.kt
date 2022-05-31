package ru.itis.karakurik.spacexapp.presentation.launchDetails

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.usecases.GetLaunchUseCase

internal class LaunchDetailsPresenterTest {
    @MockK
    lateinit var useCase: GetLaunchUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `LaunchDetailsView$$State`

    private lateinit var presenter: LaunchDetailsPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = spyk(LaunchDetailsPresenter(useCase))
        presenter.setViewState(viewState)
    }

    @Test
    fun onGetLaunchClickTest() {
        val expectedId = "launchId"
        val expectedName = "launchName"
        val expectedLaunch = mockk<Launch> { every { name } returns expectedName }
        every { useCase(expectedId) } returns Single.just(expectedLaunch)

        presenter.onGetLaunch(expectedId)

        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify { viewState.showLaunchData(expectedLaunch) }
    }

    @Test
    fun onGetLaunchClickErrorTest() {
        val expectedId = "launchError"
        val error = mockk<Throwable>()
        every { useCase(expectedId) } returns Single.error(error)

        presenter.onGetLaunch(expectedId)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify(inverse = true) {
            viewState.showLaunchData(any())
        }

        verify {
            viewState.consumerError(error)
        }
    }
}