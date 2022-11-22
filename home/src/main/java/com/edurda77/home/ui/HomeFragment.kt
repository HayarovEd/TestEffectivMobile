package com.edurda77.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.home.R
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.home.presentation.BestSellerAdapter
import com.edurda77.home.presentation.HomeViewModel
import com.edurda77.home.presentation.HotSalesAdapter
import com.edurda77.home.utils.StateCategory
import com.edurda77.home.utils.ViewShower
import com.edurda77.home.utils.StateHome
import com.edurda77.mylibrary.data.entity.BestSeller
import com.edurda77.mylibrary.data.entity.HomeStore
import com.edurda77.mylibrary.data.navigation.Action
import com.edurda77.mylibrary.data.navigation.AppNavigation
import com.edurda77.domain.entity.ItemBest
import com.edurda77.domain.entity.ItenHome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), HotSalesAdapter.BuyItemInterface {

    private var _binding: FragmentHomeBinding? = null
    private var stateCategory: StateCategory = StateCategory.ShowAll
    private val viewModel by viewModels<HomeViewModel>()
    @Inject
    lateinit var coordinator: AppNavigation

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
        switchCategory()
        initSpinner()
        initFilterSpinners()
        ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        viewModel.shopData.observe(viewLifecycleOwner) {
            when (it) {
                is StateHome.Success -> {
                    initHotSalesRecyclerView(it.data.homeStore)
                    initBestSellerRecyclerView(it.data.bestSeller)
                }
                is StateHome.Error -> {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
                is StateHome.Loading -> {}
            }
        }
        binding.filterBt.setOnClickListener {
            showFilter()
        }

        binding.itemFilter.cancelBt.setOnClickListener {
            hideFilter()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchCategory() {
        binding.phonesBt.setOnClickListener {
            stateCategory = StateCategory.ShowPhones
            ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.computersBt.setOnClickListener {
            stateCategory = StateCategory.ShowComputers
            ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.healthBt.setOnClickListener {
            stateCategory = StateCategory.ShowHealth
            ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.booksBt.setOnClickListener {
            stateCategory = StateCategory.ShowBooks
            ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.othersBt.setOnClickListener {
            stateCategory = StateCategory.ShowOthers
            ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        }
        binding.viewAll.setOnClickListener {
            stateCategory = StateCategory.ShowAll
            ViewShower(binding, stateCategory, requireContext()).initStateCategory()
        }
    }

    override fun onBuyIconClick(homeStore: com.edurda77.domain.entity.ItenHome) {
        Toast.makeText(requireContext(), "${homeStore.title} Not today", Toast.LENGTH_LONG).show()
    }

    private fun initHotSalesRecyclerView(data: List<com.edurda77.domain.entity.ItenHome>) {
        val recyclerView: RecyclerView = binding.hotSalesVp
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = HotSalesAdapter(data, this)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(Integer.MAX_VALUE/2)
    }


    private fun initBestSellerRecyclerView(bestSeller: List<com.edurda77.domain.entity.ItemBest>) {
        val recyclerView: RecyclerView = binding.bestSellerRv
        recyclerView.layoutManager = GridLayoutManager(
            requireContext(), 2, GridLayoutManager
                .VERTICAL, false
        )
        val stateClickListener: BestSellerAdapter.OnStateClickListener =
            object : BestSellerAdapter.OnStateClickListener {
                override fun onStateClick(bestSeller: com.edurda77.domain.entity.ItemBest, position: Int) {
                    coordinator.execute(Action.HomeToProduct, bestSeller)
                }
            }
        val bestSellerAdapter = BestSellerAdapter(bestSeller, stateClickListener)
        recyclerView.adapter = bestSellerAdapter
    }

    private fun showFilter() {
        //val dialog = FilterDialogFragment()
        //dialog.show(childFragmentManager, "argument")
        binding.filterMc.isVisible = true
        binding.bestSeller.isVisible = false
        binding.bestSellerRv.isVisible = false
        binding.seeMoreBs.isVisible = false
    }

    private fun hideFilter() {
        binding.filterMc.isVisible = false
        binding.bestSeller.isVisible = true
        binding.bestSellerRv.isVisible = true
        binding.seeMoreBs.isVisible = true
    }

    private fun initSpinner() {
        val brandSpinner = binding.locationSp
        val location = requireContext().resources.getStringArray(R.array.locationNames)
        val brandAdapter = ArrayAdapter (requireContext(), R.layout.item_location_spinner, R.id.item_spinner_location_tv, location)
        brandSpinner.adapter = brandAdapter
    }

    private fun initFilterSpinners() {
        val brandSpinner = binding.itemFilter.brandSp
        val brand = requireContext().resources.getStringArray(R.array.brandNames)
        val brandAdapter = ArrayAdapter (requireContext(), R.layout.item_spinner, R.id.item_spinner_tv, brand)
        brandSpinner.adapter = brandAdapter

        val priceSpinner = binding.itemFilter.priceSp
        val prices = requireContext().resources.getStringArray(R.array.prices)
        val priceAdapter = ArrayAdapter (requireContext(), R.layout.item_spinner, R.id.item_spinner_tv, prices)
        priceSpinner.adapter = priceAdapter

        val sizeSpinner = binding.itemFilter.sizeSp
        val sizes = requireContext().resources.getStringArray(R.array.sizes)
        val sizeAdapter = ArrayAdapter (requireContext(), R.layout.item_spinner, R.id.item_spinner_tv, sizes)
        sizeSpinner.adapter = sizeAdapter
    }
}