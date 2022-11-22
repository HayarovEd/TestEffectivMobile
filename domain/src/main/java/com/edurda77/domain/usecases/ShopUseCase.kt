package com.edurda77.domain.usecases

import com.edurda77.domain.entity.ShopData

interface ShopUseCase {
    suspend fun getShopData(): ShopData
}