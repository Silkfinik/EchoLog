package com.silkfinik.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EchoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun echoDao(): EchoDao
}