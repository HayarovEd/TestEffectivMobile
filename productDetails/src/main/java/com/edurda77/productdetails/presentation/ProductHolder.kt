package com.edurda77.productdetails.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.productdetails.databinding.ItemImageProductBinding

class ProductHolder(private val binding: ItemImageProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: String) {
        Glide.with(this.itemView.context)
            .load(item)
            .placeholder(com.edurda77.mylibrary.R.drawable.ic_no_photo)
            .into(binding.imageHotSales)

    }
}