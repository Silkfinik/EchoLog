package com.silkfinik.data.datasource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EchoDao {
    /**
     * Вставляет или обновляет "эхо" в базе данных.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(echo: EchoEntity)

    /**
     * Обновляет существующее "эхо" в базе данных.
     */
    @Update
    suspend fun update(echo: EchoEntity)

    /**
     * Получает [Flow] со списком всех "эхо",
     * отсортированных по времени создания.
     */
    @Query("SELECT * FROM echoes ORDER BY timestamp DESC")
    fun getAllEchoes(): Flow<List<EchoEntity>>

    /**
     * Получает [Flow] одного "эхо" по его ID.
     */
    @Query("SELECT * FROM echoes WHERE id = :id")
    fun getEchoById(id: String): Flow<EchoEntity?>

    /**
     * Получает (одноразово) список "эхо", которые еще не были синхронизированы
     * И не привязаны к пользователю.
     */
    @Query("SELECT * FROM echoes WHERE is_synced = 0 AND user_id IS NULL")
    suspend fun getUnsyncedEchoesWithoutUserId(): List<EchoEntity>

    /**
     * Привязывает все локальные "эхо" (у которых нет user_id)
     * к указанному ID пользователя.
     * Вызывается один раз после первой аутентификации
     */
    @Query("UPDATE echoes SET user_id = :userId WHERE user_id IS NULL")
    suspend fun associateEchoesWithUser(userId: String)
}