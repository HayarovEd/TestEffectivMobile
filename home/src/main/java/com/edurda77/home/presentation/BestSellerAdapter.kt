package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.home.databinding.ItemBestSellerBinding
import com.edurda77.mylibrary.domain.entity.ItemBest

class BestSellerAdapter(
    private val dataList: List<ItemBest>,
    private val onClickListener: OnStateClickListener
) : RecyclerView.Adapter<BestSellerHolder>() {
    interface OnStateClickListener {
        fun onStateClick(bestSeller: ItemBest, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BestSellerHolder(ItemBestSellerBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: BestSellerHolder, position: Int) {
        val bestSeller: ItemBest = dataList[position]
        holder.bind(bestSeller)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(bestSeller, position)
        }
    }

    override fun getItemCount(): Int = dataList.size
}