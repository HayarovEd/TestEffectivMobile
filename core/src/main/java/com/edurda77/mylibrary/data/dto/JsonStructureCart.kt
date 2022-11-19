package com.edurda77.mylibrary.data.dto


import com.google.gson.annotations.SerializedName

data class JsonStructureCart(
    @SerializedName("basket")
    val basket: List<Basket>,
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("total")
    val total: Int
)