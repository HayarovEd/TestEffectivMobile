package com.edurda77.mylibrary.data.repositories

import com.edurda77.domain.repository.NetworkRepository
import com.edurda77.mylibrary.data.api.ApiService
import com.edurda77.mylibrary.data.entity.JsonStructureAllData
import com.edurda77.mylibrary.data.entity.JsonStructureCart
import com.edurda77.mylibrary.data.entity.JsonStructureItem
import java.io.IOException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService)
    : NetworkRepository {

    override suspend fun getResponseAllData(): com.edurda77.domain.entity.ShopData {
        val response = try {
            apiService.getAllData()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return jsonStructureAllDataToShopData(response.body()!!)
        } else {
            throw com.edurda77.domain.exceptions.GenericException()
        }
    }

    override suspend fun getResponseItem(): com.edurda77.domain.entity.ProductData {
        val response = try {
            apiService.getItem()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return jsonStructureItemToCartData(response.body()!!)
        } else {
            throw com.edurda77.domain.exceptions.GenericException()
        }
    }

    override suspend fun getResponseBasket(): com.edurda77.domain.entity.CartData {
        val response = try {
            apiService.getBasket()
        } catch (error: IOException) {
            throw error
        }
        if (response.isSuccessful) {
            return jsonStructureCartToCartData(response.body()!!)
        } else {
            throw com.edurda77.domain.exceptions.GenericException()
        }
    }

    private fun jsonStructureAllDataToShopData(allData: JsonStructureAllData): com.edurda77.domain.entity.ShopData {
        return com.edurda77.domain.entity.ShopData(
            bestSeller = allData.bestSeller.map {
                com.edurda77.domain.entity.ItemBest(
                    discountPrice = it.discountPrice,
                    id = it.id,
                    isFavorites = it.isFavorites,
                    picture = it.picture,
                    priceWithoutDiscount = it.priceWithoutDiscount,
                    title = it.title
                )
            },
            homeStore = allData.homeStore.map {
                com.edurda77.domain.entity.ItenHome(
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

    private fun jsonStructureItemToCartData(productData: JsonStructureItem): com.edurda77.domain.entity.ProductData {
        return com.edurda77.domain.entity.ProductData(
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

    private fun jsonStructureCartToCartData(jsonStructureCart: JsonStructureCart): com.edurda77.domain.entity.CartData {
        return com.edurda77.domain.entity.CartData(
            basket = jsonStructureCart.basket.map {
                com.edurda77.domain.entity.ItemCart(
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