package com.edurda77.domain.usecases

import com.edurda77.domain.entity.ProductData

interface ProductUseCase {

    suspend fun getProductData(): ProductData
}