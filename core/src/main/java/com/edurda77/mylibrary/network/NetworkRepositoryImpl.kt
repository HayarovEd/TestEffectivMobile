package com.edurda77.mylibrary.network

import java.io.IOException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService)
    : NetworkRepository {

    override suspend fun getResponseAllData(): ResultNetwork {
        val response = try {
            apiService.getAllData()
        } catch (e: IOException) {
            return ResultNetwork.Error(e)
        }
        return if (response.isSuccessful) {
            ResultNetwork.Success(response.body()!!)
        } else {
            ResultNetwork.Error(Throwable(response.errorBody().toString()))
        }
    }

    override suspend fun getResponseItem(): ResultNetwork {
        val response = try {
            apiService.getItem()
        } catch (e: IOException) {
            return ResultNetwork.Error(e)
        }
        return if (response.isSuccessful) {
            ResultNetwork.SuccessItem(response.body()!!)
        } else {
            ResultNetwork.Error(Throwable(response.errorBody().toString()))
        }
    }
}