package com.edurda77.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.edurda77.home.R
import com.edurda77.home.databinding.FragmentFilterDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelBt.setOnClickListener {
            dismiss()
        }
        initSpinners()
    }

    private fun initSpinners() {
        val brandSpinner = binding.brandSp
        val brand = requireContext().resources.getStringArray(R.array.brandNames)
        val brandAdapter = ArrayAdapter (requireContext(), R.layout.item_spinner, R.id.item_spinner_tv, brand)
        brandSpinner.adapter = brandAdapter

        val priceSpinner = binding.priceSp
        val prices = requireContext().resources.getStringArray(R.array.prices)
        val priceAdapter = ArrayAdapter (requireContext(), R.layout.item_spinner, R.id.item_spinner_tv, prices)
        priceSpinner.adapter = priceAdapter

        val sizeSpinner = binding.sizeSp
        val sizes = requireContext().resources.getStringArray(R.array.sizes)
        val sizeAdapter = ArrayAdapter (requireContext(), R.layout.item_spinner, R.id.item_spinner_tv, sizes)
        sizeSpinner.adapter = sizeAdapter
    }
}