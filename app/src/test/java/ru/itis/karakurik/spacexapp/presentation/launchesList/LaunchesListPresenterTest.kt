package ru.itis.karakurik.spacexapp.presentation.launchesList

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
import ru.itis.karakurik.spacexapp.domain.usecases.GetLaunchesUseCase

internal class LaunchesListPresenterTest {
    @MockK
    lateinit var useCase: GetLaunchesUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `LaunchesListView$$State`

    private lateinit var presenter: LaunchesListPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = spyk(LaunchesListPresenter(useCase))
        presenter.setViewState(viewState)
    }

    @Test
    fun onGetLaunchesClickTest() {
        val expectedList = arrayListOf<Launch>(
            mockk { every { id } returns "launch1" },
            mockk { every { id } returns "launch2" },
        )
        every { useCase() } returns Single.just(expectedList)

        presenter.onGetLaunchesList()
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify { viewState.showLaunchesListData(expectedList) }
    }

    @Test
    fun onGetLAunchesClickException() {
        val expectedList = arrayListOf<Launch>(
            mockk { every { id } returns "launcherr" },
            mockk { every { id } returns "Launch2" },
        )

        val error = mockk<Throwable>()
        every { useCase() } returns Single.error(error)

        presenter.onGetLaunchesList()
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify(inverse = true) {
            viewState.showLaunchesListData(expectedList)
        }
        verify {
            viewState.consumerError(error)
        }
    }
}