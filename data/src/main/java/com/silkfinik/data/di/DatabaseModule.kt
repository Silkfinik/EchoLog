package com.silkfinik.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.silkfinik.data.datasource.local.db.AppDatabase
import com.silkfinik.data.datasource.local.db.EchoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "echolog_app.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEchoDao (database: AppDatabase): EchoDao {
        return database.echoDao()
    }
}