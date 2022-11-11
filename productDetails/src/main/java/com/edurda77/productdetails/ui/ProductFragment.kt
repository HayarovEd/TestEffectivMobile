package com.edurda77.productdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edurda77.mylibrary.navigation.AppNavigation
import com.edurda77.productdetails.databinding.FragmentProductBinding
import com.edurda77.productdetails.utils.ProductData
import com.edurda77.productdetails.utils.StateDataFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private var productData: ProductData = ProductData.Shop
    private val binding get() = _binding!!

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
        StateDataFactory(binding, productData, requireContext()).initStateCategory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchCategory() {
        binding.itemDetails.shopTv.setOnClickListener {
            productData = ProductData.Shop
            StateDataFactory(binding, productData, requireContext()).initStateCategory()
        }
        binding.itemDetails.detailsTv.setOnClickListener {
            productData = ProductData.Details
            StateDataFactory(binding, productData, requireContext()).initStateCategory()
        }
        binding.itemDetails.featuresTv.setOnClickListener {
            productData = ProductData.Features
            StateDataFactory(binding, productData, requireContext()).initStateCategory()
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


}