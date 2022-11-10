package com.edurda77.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
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
import com.edurda77.home.utils.StateCategoryFactory
import com.edurda77.home.utils.StateHome
import com.edurda77.mylibrary.entity.BestSeller
import com.edurda77.mylibrary.entity.HomeStore
import dagger.hilt.android.AndroidEntryPoint

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
        switchCategory()
        initSpinner()
        StateCategoryFactory(binding, stateCategory, requireContext()).initStateCategory()
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
            showDialogFilter()
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

    private fun initHotSalesRecyclerView(data: List<HomeStore>) {
        val recyclerView: RecyclerView = binding.hotSalesVp
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = HotSalesAdapter(data, this)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(Integer.MAX_VALUE/2)
    }


    private fun initBestSellerRecyclerView(bestSeller: List<BestSeller>) {
        val recyclerView: RecyclerView = binding.bestSellerRv
        recyclerView.layoutManager = GridLayoutManager(
            requireContext(), 2, GridLayoutManager
                .VERTICAL, false
        )
        val stateClickListener: BestSellerAdapter.OnStateClickListener =
            object : BestSellerAdapter.OnStateClickListener {
                override fun onStateClick(bestSeller: BestSeller, position: Int) {
                   /* val intent = Intent(this@MainActivity, MovieActivity::class.java)
                    intent.putExtra(TRANSFER_ID, moveInList.id)
                    startActivity(intent)*/
                    Toast.makeText(requireContext(), "pressed", Toast.LENGTH_LONG).show()
                }
            }
        val bestSellerAdapter = BestSellerAdapter(bestSeller, stateClickListener)
        recyclerView.adapter = bestSellerAdapter
    }

    private fun showDialogFilter() {
        val dialog = FilterDialogFragment()
        dialog.show(childFragmentManager, "argument")
    }

    private fun initSpinner() {
        val brandSpinner = binding.locationSp
        val location = requireContext().resources.getStringArray(R.array.locationNames)
        val brandAdapter = ArrayAdapter (requireContext(), R.layout.item_location_spinner, R.id.item_spinner_location_tv, location)
        brandSpinner.adapter = brandAdapter
    }
}