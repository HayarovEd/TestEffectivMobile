package com.edurda77.testeffectivmobile.utils

import com.edurda77.domain.entity.CartData

sealed interface StateMainActivity{
    object Loading : StateMainActivity
    class Success(val data: com.edurda77.domain.entity.CartData) : StateMainActivity
    class Error(val error: Throwable) : StateMainActivity
}