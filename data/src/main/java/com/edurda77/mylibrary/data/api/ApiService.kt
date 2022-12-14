package com.edurda77.mylibrary.data.api

import com.edurda77.mylibrary.data.entity.JsonStructureAllData
import com.edurda77.mylibrary.data.entity.JsonStructureCart
import com.edurda77.mylibrary.data.entity.JsonStructureItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getAllData(
    ): Response<JsonStructureAllData>

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getItem(
    ): Response<JsonStructureItem>

    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getBasket(
    ): Response<JsonStructureCart>
}