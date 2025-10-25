package com.silkfinik.data.di

import com.silkfinik.data.repository.EchoRepositoryImpl
import com.silkfinik.domain.repository.EchoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEchoRepository(
        echoRepositoryImpl: EchoRepositoryImpl
    ): EchoRepository
}