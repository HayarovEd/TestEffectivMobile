package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.home.databinding.ItemHotSalesBinding
import com.edurda77.mylibrary.data.entity.HomeStore
import com.edurda77.mylibrary.domain.entity.ItenHome

class HotSalesAdapter(
    private val dataList: List<ItenHome>,
    private val buyItemInterface: BuyItemInterface
) : RecyclerView.Adapter<HotSalesHolder>() {
    interface BuyItemInterface {
        fun onBuyIconClick(homeStore: ItenHome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HotSalesHolder(ItemHotSalesBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: HotSalesHolder, position: Int) {
        val positionInList = position % dataList.size
        val homeStore: ItenHome = dataList[positionInList]
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