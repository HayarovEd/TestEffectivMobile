package com.edurda77.mylibrary.data.repositories

import com.edurda77.mylibrary.data.api.ApiService
import com.edurda77.mylibrary.data.entity.JsonStructureAllData
import com.edurda77.mylibrary.data.entity.JsonStructureCart
import com.edurda77.mylibrary.data.entity.JsonStructureItem
import com.edurda77.mylibrary.domain.exceptions.GenericException
import com.edurda77.mylibrary.domain.entity.*
import com.edurda77.mylibrary.domain.repository.NetworkRepository
import java.io.IOException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService)
    : NetworkRepository {

    override suspend fun getResponseAllData(): ShopData {
        val response = try {
            apiService.getAllData()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return jsonStructureAllDataToShopData(response.body()!!)
        } else {
            throw GenericException()
        }
    }

    override suspend fun getResponseItem(): ProductData {
        val response = try {
            apiService.getItem()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return jsonStructureItemToCartData(response.body()!!)
        } else {
            throw GenericException()
        }
    }

    override suspend fun getResponseBasket(): CartData {
        val response = try {
            apiService.getBasket()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return jsonStructureCartToCartData(response.body()!!)
        } else {
            throw GenericException()
        }
    }

    private fun jsonStructureAllDataToShopData(allData: JsonStructureAllData): ShopData {
        return ShopData(
            bestSeller = allData.bestSeller.map {
                ItemBest(
                    discountPrice = it.discountPrice,
                    id = it.id,
                    isFavorites = it.isFavorites,
                    picture = it.picture,
                    priceWithoutDiscount = it.priceWithoutDiscount,
                    title = it.title
                )
            },
            homeStore = allData.homeStore.map {
                ItenHome(
                    id = it.id,
                    isBuy = it.isBuy,
                    isNew = it.isNew,
                    picture = it.picture,
                    subtitle = it.subtitle,
                    title = it.title
                )
            }
        )
    }

    private fun jsonStructureItemToCartData(productData: JsonStructureItem): ProductData {
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
    }

    private fun jsonStructureCartToCartData(jsonStructureCart: JsonStructureCart): CartData {
        return CartData(
            basket = jsonStructureCart.basket.map {
                ItemCart(
                    id = it.id,
                    images = it.images,
                    price = it.price,
                    title = it.title
                )
            },
            delivery = jsonStructureCart.delivery,
            total = jsonStructureCart.total
        )
    }
}