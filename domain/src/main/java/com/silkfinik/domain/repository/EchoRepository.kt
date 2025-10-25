package com.silkfinik.domain.repository

import com.silkfinik.domain.model.Echo
import kotlinx.coroutines.flow.Flow

interface EchoRepository {

    /**
     * Получает реактивный поток всех "эхо" пользователя.
     */
    fun getAllEchoes(): Flow<List<Echo>>

    /**
     * Получает реактивный поток одного "эхо" по ID.
     */
    fun getEchoById(id: String): Flow<Echo?>

    /**
     * Создает (сохраняет) новое "эхо".
     */
    suspend fun createEcho(echo: Echo)
}