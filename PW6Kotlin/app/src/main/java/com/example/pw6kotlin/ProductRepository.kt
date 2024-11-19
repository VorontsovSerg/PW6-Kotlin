package com.example.pw6kotlin.data

import androidx.lifecycle.LiveData
import com.example.pw6kotlin.network.ProductApi
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao
) {
    val allProducts: LiveData<List<ProductEntity>> = productDao.getAllProducts()

    suspend fun fetchAllProducts() {
        try {
            val response = productApi.getAllProducts()
            if (response.isSuccessful) {
                response.body()?.products?.let { products ->
                    productDao.deleteAllProducts()
                    productDao.insertProducts(products.map { ProductEntity.fromProduct(it) })
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
