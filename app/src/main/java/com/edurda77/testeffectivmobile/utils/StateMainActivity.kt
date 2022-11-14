package com.edurda77.testeffectivmobile.utils

import com.edurda77.mylibrary.entity.JsonStructureCart

sealed interface StateMainActivity{
    object Loading : StateMainActivity
    class Success(val data: JsonStructureCart) : StateMainActivity
    class Error(val error: Throwable) : StateMainActivity
}