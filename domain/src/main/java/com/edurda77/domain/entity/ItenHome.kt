package com.edurda77.domain.entity

import java.io.Serializable

data class ItenHome(
    val id: Int,
    val isBuy: Boolean,
    val isNew: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
) : Serializable