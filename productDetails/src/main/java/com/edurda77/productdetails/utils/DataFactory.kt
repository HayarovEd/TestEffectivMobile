package com.edurda77.productdetails.utils

import com.edurda77.mylibrary.entity.JsonStructureItem
import com.edurda77.productdetails.databinding.FragmentProductBinding

class DataFactory(private val binding: FragmentProductBinding) {
    fun setData(productData: JsonStructureItem) {
        with(binding.itemDetails) {
            titleProductTv.text = productData.title
            favoriteBt.isChecked = productData.isFavorites
            processorTv.text = productData.cPU
            cameraTv.text = productData.camera
            ramTv.text = productData.ssd
            memoryTv.text = productData.sd
            ratingProduct.rating = productData.rating.toFloat()
            addToChartIb.text = "Add to Cart  ${productData.price} $"
        }
    }
}