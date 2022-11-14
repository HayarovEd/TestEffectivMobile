package com.edurda77.cart.utils

import com.edurda77.mylibrary.entity.JsonStructureCart

sealed interface StateCart {
    object Loading : StateCart
    class Success(val data: JsonStructureCart) : StateCart
    class Error(val error: Throwable) : StateCart
}