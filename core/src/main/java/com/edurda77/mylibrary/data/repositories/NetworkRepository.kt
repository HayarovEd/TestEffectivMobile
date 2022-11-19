package com.edurda77.mylibrary.data.repositories

import com.edurda77.mylibrary.data.dto.JsonStructureAllData
import com.edurda77.mylibrary.data.dto.JsonStructureCart
import com.edurda77.mylibrary.data.dto.JsonStructureItem

interface NetworkRepository {
    suspend fun getResponseAllData(): JsonStructureAllData

    suspend fun getResponseItem(): JsonStructureItem

    suspend fun getResponseBasket(): JsonStructureCart
}