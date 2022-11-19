package com.edurda77.home.utils

import com.edurda77.mylibrary.entity.ShopData

sealed interface StateHome {
    object Loading : StateHome
    class Success(val data: ShopData) : StateHome
    class Error(val error: Throwable) : StateHome
}