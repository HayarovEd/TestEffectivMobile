package com.edurda77.mylibrary.entity


import com.google.gson.annotations.SerializedName

data class Basket(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)