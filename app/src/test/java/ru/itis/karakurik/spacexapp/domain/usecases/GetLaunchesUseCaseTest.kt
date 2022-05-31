package ru.itis.karakurik.spacexapp.domain.usecases

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.repository.SpaceXRepository

internal class GetLaunchesUseCaseTest {

    @MockK
    lateinit var repository: SpaceXRepository

    private lateinit var useCase: GetLaunchesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = spyk(GetLaunchesUseCase(repository))
    }

    @Test
    @DisplayName("Successful getting new launches")
    operator fun invoke() {
        val expectedList = arrayListOf<Launch>(
            mockk { every { id } returns "launch1" },
            mockk { every { id } returns "launch2" },
            mockk { every { id } returns "launch3" },
        )
        every { repository.getLaunches() } returns Single.just(expectedList)

        val result = useCase()

        assertEquals("launch1", result.blockingGet()[0].id)
        assertEquals("launch2", result.blockingGet()[1].id)
        assertEquals("launch3", result.blockingGet()[2].id)
    }

    @Test
    @DisplayName("Error when try to get launches")
    fun invokeTestException() {
        val mockError = mockk<Throwable>()
        every {
            repository.getLaunches()
        } returns Single.error(mockError)

        val result = useCase()

        assertEquals(
            mockError,
            result.blockingGet()
        )
    }
}