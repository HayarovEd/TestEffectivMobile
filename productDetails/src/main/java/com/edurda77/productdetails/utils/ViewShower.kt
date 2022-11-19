package com.edurda77.productdetails.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.edurda77.mylibrary.R
import com.edurda77.productdetails.databinding.FragmentProductBinding

class ViewShower(
    private val binding: FragmentProductBinding,
    private val productData: ProductData,
    private val context: Context
) {
    fun initStateCategory() {
        when (productData) {
            is ProductData.Shop -> {
                val allTextView = listOf(
                    binding.itemDetails.detailsTv,
                    binding.itemDetails.featuresTv
                )
                val allView = listOf(
                    binding.itemDetails.lineDetails,
                    binding.itemDetails.lineFeatures
                )
                setColorCategory(
                    binding.itemDetails.shopTv,
                    binding.itemDetails.lineShop,
                    allTextView,
                    allView
                )
            }
            is ProductData.Details -> {
                val allTextView = listOf(
                    binding.itemDetails.shopTv,
                    binding.itemDetails.featuresTv
                )
                val allView = listOf(
                    binding.itemDetails.lineShop,
                    binding.itemDetails.lineFeatures
                )
                setColorCategory(
                    binding.itemDetails.detailsTv,
                    binding.itemDetails.lineDetails,
                    allTextView,
                    allView
                )
            }
            is ProductData.Features -> {
                val allTextView = listOf(
                    binding.itemDetails.detailsTv,
                    binding.itemDetails.shopTv
                )
                val allView = listOf(
                    binding.itemDetails.lineDetails,
                    binding.itemDetails.lineShop
                )
                setColorCategory(binding.itemDetails.featuresTv, binding.itemDetails.lineFeatures, allTextView, allView)
            }
        }
    }

    private fun setColorCategory(
        selectedCategory: TextView,
        selectedLine: View,
        unSelectedCategory: List<TextView>,
        unSelectedLines: List<View>
    ) {
        selectedCategory.setTextColor(context.getColor(R.color.violet))
        selectedCategory.setTypeface(null, Typeface.BOLD)
        selectedLine.isVisible = true
        unSelectedCategory.forEach {
            it.setTextColor(context.getColor(R.color.unselected_icon))
            selectedCategory.setTypeface(null, Typeface.NORMAL)
        }
        unSelectedLines.forEach {
            it.isVisible = false
        }
    }
}
