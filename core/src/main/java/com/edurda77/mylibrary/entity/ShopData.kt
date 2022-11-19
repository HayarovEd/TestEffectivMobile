package com.edurda77.mylibrary.entity

import com.edurda77.mylibrary.data.dto.BestSeller
import com.edurda77.mylibrary.data.dto.HomeStore
import java.io.Serializable

data class ShopData(
    val bestSeller: List<BestSeller>,
    val homeStore: List<HomeStore>
) : Serializable
