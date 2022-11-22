package com.edurda77.mylibrary.data.entity


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeStore(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_buy")
    val isBuy: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
) : Serializable