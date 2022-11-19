package com.edurda77.testeffectivmobile.utils

import com.edurda77.mylibrary.entity.CartData

sealed interface StateMainActivity{
    object Loading : StateMainActivity
    class Success(val data: CartData) : StateMainActivity
    class Error(val error: Throwable) : StateMainActivity
}