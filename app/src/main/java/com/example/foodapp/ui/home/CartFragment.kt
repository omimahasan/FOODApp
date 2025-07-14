package com.example.foodapp.ui.home

import OrderRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.data.Repository.CartRepository
import com.example.foodapp.databinding.FragmentCartBinding

import com.example.foodapp.viewmodel.home.CartPage.CartViewModel
import com.example.foodapp.viewmodel.home.CartPage.CartViewModelFactory

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels {
        CartViewModelFactory(CartRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderRecyclerViewAdapter(
            onAddClick = { viewModel.addItem(it) },
            onRemoveClick = { viewModel.removeItem(it) }
        )
        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrders.adapter = adapter

        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        viewModel.loadCartItems()
    }
}




