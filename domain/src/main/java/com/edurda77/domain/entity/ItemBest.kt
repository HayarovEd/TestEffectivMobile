package com.edurda77.domain.entity

import java.io.Serializable

data class ItemBest (
        val discountPrice: Int,
        val id: Int,
        val isFavorites: Boolean,
        val picture: String,
        val priceWithoutDiscount: Int,
        val title: String
) : Serializable