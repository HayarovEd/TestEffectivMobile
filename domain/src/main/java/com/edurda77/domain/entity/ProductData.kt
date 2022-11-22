package com.edurda77.domain.entity

import java.io.Serializable

data class ProductData  (
    val cPU: String,
    val camera: String,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
) : Serializable