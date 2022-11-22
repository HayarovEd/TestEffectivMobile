package com.edurda77.cart.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.cart.databinding.ItemRvBinding
import com.edurda77.domain.entity.ItemCart

class CartHolder(private val binding: ItemRvBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: com.edurda77.domain.entity.ItemCart) {
        binding.priceTv.text = "$${item.price}.00"
        val urlPoster = item.images
        Glide.with(this.itemView.context)
            .load(urlPoster)
            .placeholder(com.edurda77.mylibrary.R.drawable.ic_no_photo)
            .into(binding.imageIv)
        if (item.title.length>=15) {
            val str1 = item.title.substring(0, 14)
            val str2 = item.title.substring(15)
            binding.titleTv.text = "$str1 \n${str2}"
        } else {
            binding.titleTv.text = item.title
        }
    }
}