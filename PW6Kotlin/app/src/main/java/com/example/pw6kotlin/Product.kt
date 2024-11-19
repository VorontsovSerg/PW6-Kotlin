package com.example.pw6kotlin.network.models

data class Product(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val discountPercentage: Double? = null,
    val rating: Double? = null,
    val stock: Int? = null,
    val brand: String? = null,
    val category: String? = null,
    val thumbnail: String? = null,
    val images: List<String>? = emptyList()
)
