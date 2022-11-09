package com.edurda77.home.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.home.presentation.HomeViewModel
import com.edurda77.home.presentation.HotSalesAdapter
import com.edurda77.home.utils.PagerFactory
import com.edurda77.home.utils.StateCategory
import com.edurda77.home.utils.StateCategoryFactory
import com.edurda77.home.utils.StateHome
import com.edurda77.mylibrary.entity.HomeStore
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(), HotSalesAdapter.BuyItemInterface {

    private var _binding: FragmentHomeBinding? = null
    private var stateCategory: StateCategory = StateCategory.ShowAll
    private val viewModel by viewModels<HomeViewModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.hotSalesVp
        val pagerFactory =  PagerFactory(viewPager)
        pagerFactory.initPager()
        switchCategory()
        StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
        viewModel.shopData.observe(viewLifecycleOwner) {
            when (it) {
                is StateHome.Success -> {
                    viewPager.adapter = HotSalesAdapter(it.data.homeStore, this)
                    viewPager.setPageTransformer(pagerFactory.setTransformer())
                }
                is StateHome.Error -> {}
                is StateHome.Loading -> {}
            }
        }
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

    override fun onBuyIconClick(homeStore: HomeStore) {
        Toast.makeText(requireContext(), "${homeStore.title} Not today", Toast.LENGTH_LONG).show()
    }
}