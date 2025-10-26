package com.silkfinik.data.repository

import com.silkfinik.data.datasource.local.db.EchoDao
import com.silkfinik.data.datasource.local.db.EchoEntity
import com.silkfinik.domain.model.Echo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EchoRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var echoDao: EchoDao

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: EchoRepositoryImpl

    @Before
    fun setUp() {
        repository = EchoRepositoryImpl(echoDao, testDispatcher)
    }

    @Test
    fun `getAllEchoes - should return mapped domain models from DAO`() = runTest (testDispatcher) {
        // Arrange
        val testEntity = EchoEntity(
            id = "1",
            title = "Test",
            text = "Text",
            latitude = 1.0,
            longitude = 1.0,
            timestamp = 123L,
            userId = null,
            isSynced = false
        )
        val expectedDomainModel = Echo(
            id = "1",
            title = "Test",
            text = "Text",
            latitude = 1.0,
            longitude = 1.0,
            timestamp = 123L,
            userId = null
        )

        every { echoDao.getAllEchoes() } returns flowOf(listOf(testEntity))

        // Act
        val result = repository.getAllEchoes().first()

        // Assert
        assertEquals(listOf(expectedDomainModel), result)
    }

    @Test
    fun `createEcho - should map to entity and call DAO insert`() = runTest (testDispatcher) {
        // Arrange
        val domainModel = Echo(
            id = "1",
            title = "Test",
            text = "Text",
            latitude = 1.0,
            longitude = 1.0,
            timestamp = 123L,
            userId = "user1"
        )

        val expectedEntity = EchoEntity(
            id = "1",
            title = "Test",
            text = "Text",
            latitude = 1.0,
            longitude = 1.0,
            timestamp = 123L,
            userId = "user1",
            isSynced = false
        )

        coEvery { echoDao.insert(any()) } returns Unit

        // Act
        repository.createEcho(domainModel)

        // Assert
        coVerify(exactly = 1) { echoDao.insert(expectedEntity) }
    }
}