package com.edurda77.productdetails.utils

import com.edurda77.mylibrary.entity.JsonStructureItem

sealed interface StateProduct {
    object Loading : StateProduct
    class Success(val data: JsonStructureItem) : StateProduct
    class Error(val error: Throwable) : StateProduct
}