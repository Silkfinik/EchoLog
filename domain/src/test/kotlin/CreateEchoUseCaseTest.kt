package com.silkfinik.domain.usecase

import com.silkfinik.domain.model.Echo
import com.silkfinik.domain.repository.EchoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class EmptyTextException : Exception("Text cannot be empty")

class CreateEchoUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var repository: EchoRepository

    private lateinit var createEchoUseCase: CreateEchoUseCase

    @Before
    fun setUp() {
        createEchoUseCase = CreateEchoUseCase(repository)
    }

    @Test
    fun `invoke success - should call repository with correctly created echo model`() = runTest {
        // Arrange
        val title = "Test Title"
        val text = "Test Text"
        val lat = 10.0
        val lon = 20.0
        val userId = "user123"

        val echoSlot = slot<Echo>()
        coEvery { repository.createEcho(capture(echoSlot)) } returns Unit

        val timeBefore = System.currentTimeMillis()

        // Act
        createEchoUseCase.invoke(
            title = title,
            text = text,
            latitude = lat,
            longitude = lon,
            userId = userId
        )

        val timeAfter = System.currentTimeMillis()

        // Assert
        coVerify(exactly = 1) { repository.createEcho(any()) }

        val capturedEcho = echoSlot.captured
        assertEquals(title, capturedEcho.title)
        assertEquals(text, capturedEcho.text)
        assertEquals(lat, capturedEcho.latitude)
        assertEquals(lon, capturedEcho.longitude)
        assertEquals(userId, capturedEcho.userId)
        assertNotNull(capturedEcho.id)
        assert(capturedEcho.timestamp in timeBefore..timeAfter)
    }

    @Test
    fun `invoke validation failure - should throw EmptyTextException if text is blank`() = runTest {
        // Arrange
        val title = "Test Title"
        val blankText = "   "
        val lat = 10.0
        val lon = 20.0

        // Act & Assert
        assertFailsWith<EmptyTextException> {
            createEchoUseCase.invoke(
                title = title,
                text = blankText,
                latitude = lat,
                longitude = lon,
                userId = null
            )
        }

        coVerify(exactly = 0) { repository.createEcho(any()) }
    }
}