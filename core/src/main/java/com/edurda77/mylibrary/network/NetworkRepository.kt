package com.edurda77.mylibrary.network

interface NetworkRepository {
    suspend fun getResponseAllData(): ResultNetwork

    suspend fun getResponseItem(): ResultNetwork

    suspend fun getResponseBasket(): ResultNetwork
}