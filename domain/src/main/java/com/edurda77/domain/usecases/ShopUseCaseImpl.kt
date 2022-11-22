package com.edurda77.domain.usecases

import com.edurda77.domain.exceptions.GenericException
import com.edurda77.domain.exceptions.NetworkException
import com.edurda77.domain.repository.NetworkRepository
import com.edurda77.domain.entity.ShopData
import javax.inject.Inject

class ShopUseCaseImpl @Inject constructor(private val repository: NetworkRepository) :
    ShopUseCase {
    @Throws(NetworkException::class, GenericException::class)
    override suspend fun getShopData(): ShopData {
        try {
            return repository.getResponseAllData()
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (exception: Exception) {
            throw GenericException()
        }
    }
}