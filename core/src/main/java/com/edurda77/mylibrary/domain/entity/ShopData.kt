package com.edurda77.mylibrary.domain.entity

import java.io.Serializable

data class ShopData(
    val bestSeller: List<ItemBest>,
    val homeStore: List<ItenHome>
) : Serializable
