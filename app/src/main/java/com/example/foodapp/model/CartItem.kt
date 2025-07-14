package com.example.foodapp.model

data class CartItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val price: Double,
    var quantity: Int,
    val imageUrl: String
)
