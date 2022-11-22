package com.edurda77.domain.repository

import com.edurda77.domain.entity.CartData
import com.edurda77.domain.entity.ProductData
import com.edurda77.domain.entity.ShopData

interface NetworkRepository {
    suspend fun getResponseAllData(): ShopData

    suspend fun getResponseItem(): ProductData

    suspend fun getResponseBasket(): CartData
}