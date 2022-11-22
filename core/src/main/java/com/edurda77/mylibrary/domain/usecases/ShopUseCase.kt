package com.edurda77.mylibrary.domain.usecases

import com.edurda77.mylibrary.domain.entity.ShopData

interface ShopUseCase {
    suspend fun getShopData(): ShopData
}