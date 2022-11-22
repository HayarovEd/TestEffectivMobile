package com.edurda77.domain.usecases

import com.edurda77.domain.entity.CartData

interface CartUseCase {
    suspend fun getCartData(): CartData
}