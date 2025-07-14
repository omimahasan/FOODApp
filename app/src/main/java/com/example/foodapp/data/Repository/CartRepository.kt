package com.example.foodapp.data.Repository

import com.example.foodapp.model.CartItem

class CartRepository {
    private val cartItems = mutableListOf<CartItem>()

    fun getCartItems(): List<CartItem> = cartItems

    fun addItem(item: CartItem) {
        cartItems.add(item)
    }

    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }
}

