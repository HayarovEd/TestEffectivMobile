package com.edurda77.home.utils

import com.edurda77.mylibrary.entity.JsonStructureAllData

sealed interface StateHome {
    object Loading : StateHome
    class Success(val data: JsonStructureAllData) : StateHome
    class Error(val error: Throwable) : StateHome
}