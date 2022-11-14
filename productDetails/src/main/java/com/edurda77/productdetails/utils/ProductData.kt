package com.edurda77.productdetails.utils

sealed interface ProductData {
    object Shop :ProductData
    object Details :ProductData
    object Features :ProductData
}