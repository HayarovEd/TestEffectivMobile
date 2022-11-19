package com.edurda77.home.utils

import android.content.Context
import android.widget.ImageButton
import android.widget.TextView
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.mylibrary.R

internal class ViewShower(
    private val binding: FragmentHomeBinding,
    private val stateCategory: StateCategory,
    private val context: Context
) {
    fun initStateCategory() {
        when (stateCategory) {
            is StateCategory.ShowPhones -> {
                val allButtons = listOf<ImageButton>(
                    binding.computersBt,
                    binding.healthBt,
                    binding.booksBt,
                    binding.othersBt
                )
                val allTextView = listOf(
                    binding.computersTv,
                    binding.healthTv,
                    binding.booksTv,
                    binding.othersTv
                )
                setColorCategory(binding.phonesBt, binding.phonesTv, allButtons, allTextView)
            }
            is StateCategory.ShowComputers -> {
                val allButtons = listOf<ImageButton>(
                    binding.phonesBt,
                    binding.healthBt,
                    binding.booksBt,
                    binding.othersBt
                )
                val allTextView = listOf(
                    binding.phonesTv,
                    binding.healthTv,
                    binding.booksTv,
                    binding.othersTv
                )
                setColorCategory(binding.computersBt, binding.computersTv, allButtons, allTextView)
            }
            is StateCategory.ShowHealth -> {
                val allButtons = listOf<ImageButton>(
                    binding.computersBt,
                    binding.phonesBt,
                    binding.booksBt,
                    binding.othersBt
                )
                val allTextView = listOf(
                    binding.computersTv,
                    binding.phonesTv,
                    binding.booksTv,
                    binding.othersTv
                )
                setColorCategory(binding.healthBt, binding.healthTv, allButtons, allTextView)
            }
            is StateCategory.ShowBooks -> {
                val allButtons = listOf<ImageButton>(
                    binding.computersBt,
                    binding.healthBt,
                    binding.phonesBt,
                    binding.othersBt
                )
                val allTextView = listOf(
                    binding.computersTv,
                    binding.healthTv,
                    binding.phonesTv,
                    binding.othersTv
                )
                setColorCategory(binding.booksBt, binding.booksTv, allButtons, allTextView)
            }
            is StateCategory.ShowOthers -> {
                val allButtons = listOf<ImageButton>(
                    binding.computersBt,
                    binding.healthBt,
                    binding.booksBt,
                    binding.phonesBt
                )
                val allTextView = listOf(
                    binding.computersTv,
                    binding.healthTv,
                    binding.booksTv,
                    binding.phonesTv
                )
                setColorCategory(binding.othersBt, binding.othersTv, allButtons, allTextView)
            }
            is StateCategory.ShowAll -> {
                val allButtons = listOf<ImageButton>(
                    binding.computersBt,
                    binding.healthBt,
                    binding.booksBt,
                    binding.othersBt,
                    binding.phonesBt
                )
                val allTextView = listOf(
                    binding.computersTv,
                    binding.healthTv,
                    binding.booksTv,
                    binding.othersTv,
                    binding.phonesTv
                )
                setColorCategory(null, null, allButtons, allTextView)
            }
        }
    }

    private fun setColorCategory(
        selectedButton: ImageButton?,
        selectedCategory: TextView?,
        unSelectedButton: List<ImageButton>,
        unSelectedCategory: List<TextView>
    ) {
        if (selectedButton != null) {
            selectedButton.isActivated = true
        }
        selectedCategory?.setTextColor(context.getColor(R.color.orange))
        unSelectedButton.forEach {
            it.isActivated = false
        }
        unSelectedCategory.forEach {
            it.setTextColor(context.getColor(R.color.violet))
        }
        if (binding.phonesBt.isActivated) {
            binding.phonesBt.setImageResource(R.drawable.ic_selected_phones)
        } else {
            binding.phonesBt.setImageResource(R.drawable.ic_unselected_phones)
        }
        if (binding.computersBt.isActivated) {
            binding.computersBt.setImageResource(R.drawable.ic_selected_computer)
        } else {
            binding.computersBt.setImageResource(R.drawable.ic_unselected_computer)
        }
        if (binding.healthBt.isActivated) {
            binding.healthBt.setImageResource(R.drawable.ic_selected_health)
        } else {
            binding.healthBt.setImageResource(R.drawable.ic_unselected_health)
        }
        if (binding.booksBt.isActivated) {
            binding.booksBt.setImageResource(R.drawable.ic_selected_books)
        } else {
            binding.booksBt.setImageResource(R.drawable.ic_unselected_books)
        }
        if (binding.othersBt.isActivated) {
            binding.othersBt.setImageResource(R.drawable.ic_selected_other)
        } else {
            binding.othersBt.setImageResource(R.drawable.ic_unselected_other)
        }
    }
}