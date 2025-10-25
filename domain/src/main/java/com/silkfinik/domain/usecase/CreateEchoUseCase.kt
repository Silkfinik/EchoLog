package com.silkfinik.domain.usecase

import com.silkfinik.domain.model.Echo
import com.silkfinik.domain.repository.EchoRepository
import java.util.UUID
import javax.inject.Inject

class EmptyTextException: Exception("Echo text cannot be empty")

class CreateEchoUseCase @Inject constructor(
    private val repository: EchoRepository
) {
    suspend operator fun invoke(
        title: String,
        text: String,
        latitude: Double,
        longitude: Double,
        userId: String? = null
    ) {
        if(text.isEmpty()) {
            throw EmptyTextException()
        }

        val newEcho = Echo(
            id = UUID.randomUUID().toString(),
            title = title.trim(),
            text = text.trim(),
            latitude = latitude,
            longitude = longitude,
            timestamp = System.currentTimeMillis(),
            userId = userId
        )

        repository.createEcho(newEcho)
    }
}