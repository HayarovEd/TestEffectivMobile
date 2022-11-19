package com.edurda77.productdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.mylibrary.data.dto.JsonStructureItem
import com.edurda77.mylibrary.entity.CartData
import com.edurda77.mylibrary.navigation.Action
import com.edurda77.mylibrary.navigation.AppNavigation
import com.edurda77.productdetails.databinding.FragmentProductBinding
import com.edurda77.productdetails.presentation.ProductAdapter
import com.edurda77.productdetails.presentation.ProductFragmentViewModel
import com.edurda77.productdetails.utils.ProductData
import com.edurda77.productdetails.utils.ViewShower
import com.edurda77.productdetails.utils.StateProduct
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private var productData: ProductData = ProductData.Shop
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProductFragmentViewModel>()

    @Inject
    lateinit var coordinator: AppNavigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchCategory()
        selectColor()
        selectCapacity()
        ViewShower(binding, productData, requireContext()).initStateCategory()
        viewModel.productData.observe(viewLifecycleOwner) {
            when (it) {
                is StateProduct.Success -> {
                    initRecyclerView(it.data.images)
                    setData(it.data)
                }
                is StateProduct.Error -> {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
                is StateProduct.Loading -> {}
            }
        }
        binding.backBt.setOnClickListener {
            coordinator.execute(Action.ProductToHome, null)
        }
        binding.chartBt.setOnClickListener {
            coordinator.execute(Action.ProductToChart, null)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchCategory() {
        binding.itemDetails.shopTv.setOnClickListener {
            productData = ProductData.Shop
            ViewShower(binding, productData, requireContext()).initStateCategory()
        }
        binding.itemDetails.detailsTv.setOnClickListener {
            productData = ProductData.Details
            ViewShower(binding, productData, requireContext()).initStateCategory()
        }
        binding.itemDetails.featuresTv.setOnClickListener {
            productData = ProductData.Features
            ViewShower(binding, productData, requireContext()).initStateCategory()
        }
    }

    private fun selectColor() {
        binding.itemDetails.colorFirstCb.setOnClickListener {
            binding.itemDetails.colorFirstCb.isChecked = true
            binding.itemDetails.colorSecondCb.isChecked = false
        }
        binding.itemDetails.colorSecondCb.setOnClickListener {
            binding.itemDetails.colorFirstCb.isChecked = false
            binding.itemDetails.colorSecondCb.isChecked = true
        }
    }

    private fun selectCapacity() {
        binding.itemDetails.capacityFirstCb.setOnClickListener {
            binding.itemDetails.capacityFirstCb.isChecked = true
            binding.itemDetails.capacitySecondCb.isChecked = false
        }
        binding.itemDetails.capacitySecondCb.setOnClickListener {
            binding.itemDetails.capacityFirstCb.isChecked = false
            binding.itemDetails.capacitySecondCb.isChecked = true
        }
    }

    private fun initRecyclerView(images: List<String>) {
        val recyclerView: RecyclerView = binding.imageProductRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = ProductAdapter(images)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(Integer.MAX_VALUE/2)
    }

    fun setData(productData: com.edurda77.mylibrary.entity.ProductData) {
        with(binding.itemDetails) {
            titleProductTv.text = productData.title
            favoriteBt.isChecked = productData.isFavorites
            processorTv.text = productData.cPU
            cameraTv.text = productData.camera
            ramTv.text = productData.ssd
            memoryTv.text = productData.sd
            ratingProduct.rating = productData.rating.toFloat()
            addToChartIb.text = "Add to Cart  ${productData.price} $"
        }
    }
}