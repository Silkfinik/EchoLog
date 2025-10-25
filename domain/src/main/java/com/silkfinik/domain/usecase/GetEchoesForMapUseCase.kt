package com.silkfinik.domain.usecase

import com.silkfinik.domain.model.Echo
import com.silkfinik.domain.repository.EchoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEchoesForMapUseCase @Inject constructor(
    private val repository: EchoRepository
) {
    operator fun invoke(): Flow<List<Echo>> = repository.getAllEchoes()
}