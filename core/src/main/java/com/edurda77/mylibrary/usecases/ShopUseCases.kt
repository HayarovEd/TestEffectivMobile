package com.edurda77.mylibrary.usecases

import com.edurda77.mylibrary.entity.CartData
import com.edurda77.mylibrary.entity.ProductData
import com.edurda77.mylibrary.entity.ShopData

interface ShopUseCases {
    suspend fun getShopData(): ShopData
    suspend fun getProductData(): ProductData
    suspend fun getCartData(): CartData
}