package com.edurda77.cart.utils

import com.edurda77.mylibrary.domain.entity.CartData

sealed interface StateCart {
    object Loading : StateCart
    class Success(val data: CartData) : StateCart
    class Error(val error: Throwable) : StateCart
}