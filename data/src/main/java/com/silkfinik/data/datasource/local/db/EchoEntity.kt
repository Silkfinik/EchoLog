package com.silkfinik.data.datasource.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param id Уникальный идентификатор, используется как @PrimaryKey.
 * @param title Заголовок "эхо".
 * @param text Основной текст "эхо".
 * @param latitude Широта, на которой было создано "эхо".
 * @param longitude Долгота, на которой было создано "эхо".
 * @param timestamp Временная метка создания (Unix time).
 * @param isSynced Флаг, показывающий, синхронизирована ли эта запись
 * с облаком (Firestore).
 * @param userId Идентификатор пользователя Firebase, с которым связано "эхо".
 */
@Entity(tableName = "echoes")
data class EchoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "is_synced", defaultValue = "0")
    val isSynced: Boolean = false,

    @ColumnInfo(name = "user_id")
    val userId: String? = null,

    @ColumnInfo(name = "journal_id")
    val journalId: String? = null
)