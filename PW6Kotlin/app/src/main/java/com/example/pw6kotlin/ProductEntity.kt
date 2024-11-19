package com.example.pw6kotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pw6kotlin.network.models.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String
) {
    companion object {
        fun fromProduct(product: Product): ProductEntity {
            return ProductEntity(
                id = product.id,
                title = product.title ?: "No Title",
                description = product.description ?: "No Description",
                price = product.price ?: 0.0,
                discountPercentage = product.discountPercentage ?: 0.0,
                rating = product.rating ?: 0.0,
                stock = product.stock ?: 0,
                brand = product.brand ?: "Unknown",
                category = product.category ?: "Uncategorized",
                thumbnail = product.thumbnail ?: ""
            )
        }
    }
}
