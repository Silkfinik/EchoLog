package com.silkfinik.data.repository

import com.silkfinik.data.datasource.local.db.EchoDao
import com.silkfinik.data.di.IoDispatcher
import com.silkfinik.domain.model.Echo
import com.silkfinik.domain.repository.EchoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @param echoDao DAO для локального доступа к Room.
 * @param ioDispatcher Диспетчер для выполнения IO-операций (БД).
 */
@Singleton
class EchoRepositoryImpl @Inject constructor(
    private val echoDao: EchoDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : EchoRepository {
    override fun getAllEchoes(): Flow<List<Echo>> {
        return echoDao.getAllEchoes().map { entityList ->
            entityList.map { it.toDomain() }
        }
    }

    override fun getEchoById(id: String): Flow<Echo?> {
        return echoDao.getEchoById(id).map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun createEcho(echo: Echo) {
        withContext(ioDispatcher) {
            echoDao.insert(echo.toEntity(isSynced = false))
        }
    }
}