package com.edurda77.mylibrary.domain.usecases

import com.edurda77.mylibrary.domain.entity.CartData
import com.edurda77.mylibrary.domain.exceptions.GenericException
import com.edurda77.mylibrary.domain.exceptions.NetworkException
import com.edurda77.mylibrary.domain.repository.NetworkRepository
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(private val repository: NetworkRepository): CartUseCase {
    @Throws(NetworkException::class, GenericException::class)
    override suspend fun getCartData(): CartData {
        try {
            return repository.getResponseBasket()
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (exception: Exception) {
            throw GenericException()
        }
    }
}