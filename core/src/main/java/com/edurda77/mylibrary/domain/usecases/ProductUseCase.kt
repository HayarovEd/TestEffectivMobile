package com.edurda77.mylibrary.domain.usecases

import com.edurda77.mylibrary.domain.entity.ProductData

interface ProductUseCase {

    suspend fun getProductData(): ProductData
}