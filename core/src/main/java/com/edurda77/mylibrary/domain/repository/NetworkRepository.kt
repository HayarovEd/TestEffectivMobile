package com.edurda77.mylibrary.domain.repository

import com.edurda77.mylibrary.domain.entity.CartData
import com.edurda77.mylibrary.domain.entity.ProductData
import com.edurda77.mylibrary.domain.entity.ShopData

interface NetworkRepository {
    suspend fun getResponseAllData(): ShopData

    suspend fun getResponseItem(): ProductData

    suspend fun getResponseBasket(): CartData
}