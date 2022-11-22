package com.edurda77.mylibrary.domain.entity

import java.io.Serializable

data class ItemCart(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
): Serializable
