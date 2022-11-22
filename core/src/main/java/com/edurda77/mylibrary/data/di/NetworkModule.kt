package com.edurda77.mylibrary.data.di

import com.edurda77.mylibrary.data.api.ApiService
import com.edurda77.mylibrary.domain.repository.NetworkRepository
import com.edurda77.mylibrary.data.repositories.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )

    @Provides
    @Singleton
    fun provideMainRemoteData(apiService: ApiService): NetworkRepository =
        NetworkRepositoryImpl(apiService)

}