package com.edurda77.productdetails.utils

import com.edurda77.mylibrary.entity.ProductData

sealed interface StateProduct {
    object Loading : StateProduct
    class Success(val data: ProductData) : StateProduct
    class Error(val error: Throwable) : StateProduct
}