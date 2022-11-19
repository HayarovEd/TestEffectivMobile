package com.edurda77.mylibrary.data.repositories

import com.edurda77.mylibrary.data.dto.JsonStructureAllData
import com.edurda77.mylibrary.data.dto.JsonStructureCart
import com.edurda77.mylibrary.data.dto.JsonStructureItem
import com.edurda77.mylibrary.data.exceptions.GenericException
import com.edurda77.mylibrary.data.repositories.api.ApiService
import java.io.IOException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService)
    : NetworkRepository {

    override suspend fun getResponseAllData(): JsonStructureAllData {
        val response = try {
            apiService.getAllData()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw GenericException()
        }
    }

    override suspend fun getResponseItem(): JsonStructureItem {
        val response = try {
            apiService.getItem()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw GenericException()
        }
    }

    override suspend fun getResponseBasket(): JsonStructureCart {
        val response = try {
            apiService.getBasket()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw GenericException()
        }
    }
}