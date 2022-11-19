package com.edurda77.mylibrary.di

import com.edurda77.mylibrary.data.repositories.NetworkRepository
import com.edurda77.mylibrary.usecases.ShopUseCases
import com.edurda77.mylibrary.usecases.ShopUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(networkRepository: NetworkRepository): ShopUseCases =
        ShopUseCasesImpl(networkRepository)
}