package com.example.pw5kotlin.di

import androidx.room.Room
import com.example.pw5kotlin.AppDatabase
import com.example.pw5kotlin.ProductViewModel
import com.example.pw5kotlin.data.ProductRepository
import com.example.pw5kotlin.network.ProductApi
import com.example.pw5kotlin.network.RetrofitInstance
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Зависимость для Retrofit API
    single<ProductApi> { RetrofitInstance.api }

    // Зависимость для базы данных Room
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "product_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Зависимость для ProductDao из базы данных
    single { get<AppDatabase>().productDao() }

    // Зависимость для ProductRepository
    single { ProductRepository(get(), get()) }

    // ViewModel
    viewModel { ProductViewModel(get()) }
}
