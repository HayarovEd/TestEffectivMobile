package com.edurda77.mylibrary.domain.di

import com.edurda77.mylibrary.domain.repository.NetworkRepository
import com.edurda77.mylibrary.domain.usecases.*
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
    fun provideShopUseCase(networkRepository: NetworkRepository): ShopUseCase =
        ShopUseCaseImpl(networkRepository)

    @Provides
    @Singleton
    fun provideProductUseCase(networkRepository: NetworkRepository): ProductUseCase =
        ProductUseCaseImpl(networkRepository)

    @Provides
    @Singleton
    fun provideCartUseCase(networkRepository: NetworkRepository): CartUseCase =
        CartUseCaseImpl(networkRepository)
}