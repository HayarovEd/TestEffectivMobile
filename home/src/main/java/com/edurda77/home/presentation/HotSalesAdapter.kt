package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.home.databinding.ItemHotSalesBinding
import com.edurda77.mylibrary.entity.HomeStore

class HotSalesAdapter(
    private val dataList: List<HomeStore>,
    private val buyItemInterface: BuyItemInterface
) : RecyclerView.Adapter<HotSalesHolder>() {
    interface BuyItemInterface {
        fun onBuyIconClick(homeStore: HomeStore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HotSalesHolder(ItemHotSalesBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: HotSalesHolder, position: Int) {
        val positionInList = position % dataList.size
        val homeStore: HomeStore = dataList[positionInList]
        holder.bind(homeStore)
        holder.onBuyClick.setOnClickListener{
            buyItemInterface.onBuyIconClick(homeStore)
        }
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }
}