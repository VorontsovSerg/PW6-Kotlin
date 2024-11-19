package com.example.pw6kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pw6kotlin.data.ProductEntity
import com.example.pw6kotlin.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    val allProducts: LiveData<List<ProductEntity>> = repository.allProducts

    fun fetchAllProducts() = viewModelScope.launch {
        repository.fetchAllProducts()
    }
}
