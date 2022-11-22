package com.edurda77.domain.usecases

import com.edurda77.domain.entity.ProductData
import com.edurda77.domain.exceptions.GenericException
import com.edurda77.domain.exceptions.NetworkException
import com.edurda77.domain.repository.NetworkRepository
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(private val repository: NetworkRepository) :
    ProductUseCase {
    @Throws(NetworkException::class, GenericException::class)
    override suspend fun getProductData(): ProductData {
        try {
            return repository.getResponseItem()
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (exception: Exception) {
            throw GenericException()
        }
    }
}