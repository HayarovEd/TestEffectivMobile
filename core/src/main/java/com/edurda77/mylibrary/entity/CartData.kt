package com.edurda77.mylibrary.entity

import com.edurda77.mylibrary.data.dto.Basket

data class CartData(
    val basket: List<Basket>,
    val delivery: String,
    val total: Int
)
