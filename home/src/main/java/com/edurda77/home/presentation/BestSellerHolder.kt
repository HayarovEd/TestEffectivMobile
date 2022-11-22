package com.edurda77.home.presentation

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.home.databinding.ItemBestSellerBinding
import com.edurda77.mylibrary.domain.entity.ItemBest

class BestSellerHolder(private val binding: ItemBestSellerBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind (item: ItemBest) {
            //binding.favoriteCb.isChecked = item.isFavorites
            binding.salePriceTv.text = "$${item.discountPrice}"
            binding.priceTv.text = "$${item.priceWithoutDiscount}"
            binding.priceTv.paintFlags = binding.priceTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            val urlPoster = item.picture
            Glide.with(this.itemView.context)
                .load(urlPoster)
                //.placeholder(com.edurda77.mylibrary.R.drawable.ic_no_photo)
                .into(binding.imageBestSeller)
            binding.modelTv.text = item.title
        }
}