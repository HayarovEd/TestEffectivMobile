package com.edurda77.mylibrary.domain.usecases

import com.edurda77.mylibrary.domain.entity.CartData

interface CartUseCase {
    suspend fun getCartData(): CartData
}