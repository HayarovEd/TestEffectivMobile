package com.edurda77.productdetails.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.productdetails.databinding.ItemImageProductBinding

class ProductAdapter  (private val dataList: List<String>) : RecyclerView.Adapter<ProductHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductHolder(ItemImageProductBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val positionInList = position % dataList.size
        val pathPicture = dataList[positionInList]
        holder.bind(pathPicture)
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }

}