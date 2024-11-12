package com.example.pw5kotlin.network

import com.example.pw5kotlin.network.models.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getAllProducts(): ProductResponse
}
