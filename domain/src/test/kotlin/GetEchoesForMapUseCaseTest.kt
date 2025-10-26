package com.silkfinik.domain.usecase

import com.silkfinik.domain.model.Echo
import com.silkfinik.domain.repository.EchoRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetEchoesForMapUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var repository: EchoRepository

    private lateinit var getEchoesForMapUseCase: GetEchoesForMapUseCase

    @Before
    fun setUp() {
        getEchoesForMapUseCase = GetEchoesForMapUseCase(repository)
    }

    @Test
    fun `invoke - should call repository getAllEchoes and return its flow`() = runTest {
        // Arrange
        val testEcho = Echo("1", "Title", "Text", 0.0, 0.0, 123L, null)
        val expectedFlow = flowOf(listOf(testEcho))

        every { repository.getAllEchoes() } returns expectedFlow

        // Act
        val resultFlow = getEchoesForMapUseCase()

        // Assert
        assertEquals(expectedFlow, resultFlow)

        assertEquals(listOf(testEcho), resultFlow.first())

        verify(exactly = 1) { repository.getAllEchoes() }
    }
}