package com.edurda77.cart.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.cart.databinding.ItemRvBinding
import com.edurda77.mylibrary.entity.Basket

class CartAdapter(private val dataList: List<Basket>) : RecyclerView.Adapter<CartHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartHolder(ItemRvBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val basket = dataList[position]
        holder.bind(basket)
    }

    override fun getItemCount(): Int = dataList.size
}