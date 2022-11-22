package com.edurda77.cart.utils

import com.edurda77.domain.entity.CartData

sealed interface StateCart {
    object Loading : StateCart
    class Success(val data: com.edurda77.domain.entity.CartData) : StateCart
    class Error(val error: Throwable) : StateCart
}