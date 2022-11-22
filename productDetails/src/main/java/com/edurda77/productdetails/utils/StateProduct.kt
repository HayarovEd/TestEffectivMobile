package com.edurda77.productdetails.utils

import com.edurda77.domain.entity.ProductData

sealed interface StateProduct {
    object Loading : StateProduct
    class Success(val data: com.edurda77.domain.entity.ProductData) : StateProduct
    class Error(val error: Throwable) : StateProduct
}