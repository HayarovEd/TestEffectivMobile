package com.edurda77.mylibrary.data.dto


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonStructureAllData(
    @SerializedName("best_seller")
    val bestSeller: List<BestSeller>,
    @SerializedName("home_store")
    val homeStore: List<HomeStore>
) : Serializable