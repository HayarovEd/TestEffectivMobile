package com.edurda77.home.utils

import com.edurda77.domain.entity.ShopData

sealed interface StateHome {
    object Loading : StateHome
    class Success(val data: com.edurda77.domain.entity.ShopData) : StateHome
    class Error(val error: Throwable) : StateHome
}