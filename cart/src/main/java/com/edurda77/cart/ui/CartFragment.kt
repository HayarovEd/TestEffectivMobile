package com.edurda77.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.cart.databinding.FragmentCartBinding
import com.edurda77.cart.presentation.CartAdapter
import com.edurda77.cart.presentation.CartViewModel
import com.edurda77.cart.utils.StateCart
import com.edurda77.mylibrary.entity.Basket
import com.edurda77.mylibrary.entity.HomeStore
import com.edurda77.mylibrary.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val viewModel by viewModels<CartViewModel>()
    @Inject
    lateinit var coordinator: AppNavigation
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.shopData.observe(viewLifecycleOwner) {
            when (it) {
                is StateCart.Success -> {
                    initRecyclerView(it.data.basket)
                }
                is StateCart.Error -> {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
                is StateCart.Loading -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(data: List<Basket>) {
        val recyclerView: RecyclerView = binding.itemCart.cartRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        val adapter = CartAdapter(data)
        recyclerView.adapter = adapter
    }
}