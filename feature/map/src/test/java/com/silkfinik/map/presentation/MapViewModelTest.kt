package com.silkfinik.map.presentation

import app.cash.turbine.test
import com.silkfinik.domain.model.Echo
import com.silkfinik.domain.usecase.GetEchoesForMapUseCase
import com.silkfinik.map.presentation.model.MapMarkerUiModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MapViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getEchoesForMapUseCase: GetEchoesForMapUseCase

    private lateinit var viewModel: MapViewModel

    @Test
    fun `init - should load echoes and update state to success`() = runTest {
        // Arrange
        val domainEcho = Echo("1", "Test", "Text", 10.0, 20.0, 123L, null)
        val expectedUiModel = MapMarkerUiModel("1", 10.0, 20.0)
        every { getEchoesForMapUseCase() } returns flowOf(listOf(domainEcho))

        // Act
        viewModel = MapViewModel(getEchoesForMapUseCase)

        // Assert
        viewModel.state.test {
            assertEquals(
                MapScreenState(isLoading = true, markers = emptyList()),
                awaitItem()
            )

            advanceUntilIdle()

            assertEquals(
                MapScreenState(isLoading = false, markers = listOf(expectedUiModel)),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `init - should handle empty list from use case`() = runTest {
        // Arrange
        every { getEchoesForMapUseCase() } returns flowOf(emptyList())

        // Act
        viewModel = MapViewModel(getEchoesForMapUseCase)

        // Assert
        viewModel.state.test {
            assertEquals(
                MapScreenState(isLoading = true, markers = emptyList()),
                awaitItem()
            )

            advanceUntilIdle()

            assertEquals(
                MapScreenState(isLoading = false, markers = emptyList()),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
}