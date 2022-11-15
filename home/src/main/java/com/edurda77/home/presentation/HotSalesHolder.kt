package com.edurda77.home.presentation

import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.home.databinding.ItemHotSalesBinding
import com.edurda77.mylibrary.entity.HomeStore

class HotSalesHolder(private val binding: ItemHotSalesBinding) :
    RecyclerView.ViewHolder(binding.root) {

        val onBuyClick: AppCompatButton = binding.buyBt

        fun bind (item: HomeStore) {
            binding.newIv.isVisible = item.isNew
            val urlPoster = item.picture
            Glide.with(this.itemView.context)
                .load(urlPoster)
                //.placeholder(com.edurda77.mylibrary.R.drawable.ic_no_photo)
                .into(binding.imageHotSales)
            binding.titleTv.text = item.title
            binding.subtitleTv.text = item.subtitle
        }
}