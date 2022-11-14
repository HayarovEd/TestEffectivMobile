package com.edurda77.mylibrary.network

import com.edurda77.mylibrary.entity.JsonStructureAllData
import com.edurda77.mylibrary.entity.JsonStructureCart
import com.edurda77.mylibrary.entity.JsonStructureItem

sealed interface ResultNetwork {
    class Success (val data: JsonStructureAllData) : ResultNetwork
    class SuccessItem (val data: JsonStructureItem) : ResultNetwork
    class SuccessBasket (val data: JsonStructureCart) : ResultNetwork
    class Error(val error: Throwable) : ResultNetwork
}