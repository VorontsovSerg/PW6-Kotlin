package com.example.pw6kotlin.network

import com.example.pw6kotlin.network.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): Response<ProductResponse>
}
