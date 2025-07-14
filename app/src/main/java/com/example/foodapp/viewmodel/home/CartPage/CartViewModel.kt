package com.example.foodapp.viewmodel.home.CartPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.Repository.CartRepository
import com.example.foodapp.model.CartItem

class CartViewModel(private val repository: CartRepository) : ViewModel() {
    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> get() = _cartItems

    fun loadCartItems() {
        _cartItems.value = repository.getCartItems()
    }

    fun addItem(item: CartItem) {
        repository.addItem(item)
        loadCartItems()
    }

    fun removeItem(item: CartItem) {
        repository.removeItem(item)
        loadCartItems()
    }
}


