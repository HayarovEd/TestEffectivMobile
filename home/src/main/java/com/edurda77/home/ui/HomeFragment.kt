package com.edurda77.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.home.presentation.HomeViewModel
import com.edurda77.home.utils.StateCategory
import com.edurda77.home.utils.StateCategoryFactory
import com.edurda77.mylibrary.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var stateCategory: StateCategory = StateCategory.ShowAll

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchCategory()
        StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchCategory() {
        binding.phonesBt.setOnClickListener {
            stateCategory = StateCategory.ShowPhones
            StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.computersBt.setOnClickListener {
            stateCategory = StateCategory.ShowComputers
            StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.healthBt.setOnClickListener {
            stateCategory = StateCategory.ShowHealth
            StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.booksBt.setOnClickListener {
            stateCategory = StateCategory.ShowBooks
            StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.othersBt.setOnClickListener {
            stateCategory = StateCategory.ShowOthers
            StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.viewAll.setOnClickListener {
            stateCategory = StateCategory.ShowAll
            StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        }
    }


}