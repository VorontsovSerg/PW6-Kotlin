package com.example.pw5kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pw5kotlin.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация Koin
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        val buttonFetchData = findViewById<Button>(R.id.buttonFetchData)
        val buttonShowList = findViewById<Button>(R.id.buttonShowList)

        // Кнопка для загрузки всех данных из API и сохранения их в базе данных
        buttonFetchData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    productViewModel.fetchAllProducts()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        // Кнопка для перехода ко второй активности для отображения списка продуктов
        buttonShowList.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }
    }
}
