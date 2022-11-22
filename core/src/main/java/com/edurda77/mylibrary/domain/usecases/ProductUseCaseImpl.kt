package com.edurda77.mylibrary.domain.usecases

import com.edurda77.mylibrary.domain.entity.ProductData
import com.edurda77.mylibrary.domain.exceptions.GenericException
import com.edurda77.mylibrary.domain.exceptions.NetworkException
import com.edurda77.mylibrary.domain.repository.NetworkRepository
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