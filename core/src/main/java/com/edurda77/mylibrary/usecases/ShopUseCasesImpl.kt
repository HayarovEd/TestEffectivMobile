package com.edurda77.mylibrary.usecases

import com.edurda77.mylibrary.data.exceptions.GenericException
import com.edurda77.mylibrary.data.exceptions.NetworkException
import com.edurda77.mylibrary.data.repositories.NetworkRepository
import com.edurda77.mylibrary.entity.CartData
import com.edurda77.mylibrary.entity.ProductData
import com.edurda77.mylibrary.entity.ShopData
import javax.inject.Inject

class ShopUseCasesImpl @Inject constructor(private val repository: NetworkRepository) :
    ShopUseCases {
    @Throws(NetworkException::class, GenericException::class)
    override suspend fun getShopData(): ShopData {
        try {
            val allData = repository.getResponseAllData()
            return ShopData(
                allData.bestSeller,
                allData.homeStore
            )
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (exception: Exception) {
            throw GenericException()
        }
    }

    @Throws(NetworkException::class, GenericException::class)
    override suspend fun getProductData(): ProductData {
        try {
            val productData = repository.getResponseItem()
            return ProductData(
                cPU = productData.cPU,
                camera = productData.camera,
                images = productData.images,
                isFavorites = productData.isFavorites,
                price = productData.price,
                rating = productData.rating,
                sd = productData.sd,
                ssd = productData.ssd,
                title = productData.title
            )
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (exception: Exception) {
            throw GenericException()
        }
    }

    @Throws(NetworkException::class, GenericException::class)
    override suspend fun getCartData(): CartData {
        try {
            val cartData = repository.getResponseBasket()
            return CartData(
                basket = cartData.basket,
                delivery = cartData.delivery,
                total = cartData.total
            )
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (exception: Exception) {
            throw GenericException()
        }
    }

}