package com.edurda77.mylibrary.domain.entity

data class CartData(
    val basket: List<ItemCart>,
    val delivery: String,
    val total: Int
)
