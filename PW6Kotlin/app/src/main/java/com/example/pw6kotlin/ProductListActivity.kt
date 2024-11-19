package com.example.pw6kotlin

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pw6kotlin.data.ProductEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutProducts)

        productViewModel.allProducts.observe(this, Observer { products ->
            if (products != null) {
                tableLayout.removeViews(1, tableLayout.childCount - 1)

                for (product in products) {
                    val row = TableRow(this)
                    row.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    val idText = product.id.toString()
                    val titleText = product.title ?: "No Title"
                    val priceText = product.price?.let { "$it" } ?: "Price not available"

                    val idTextView = createTextView(idText, 0.15f, Gravity.CENTER)
                    val titleTextView = createTextView(titleText, 0.4f, Gravity.START)
                    val priceTextView = createTextView(priceText, 0.2f, Gravity.CENTER)

                    val imageView = ImageView(this).apply {
                        layoutParams = TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            0.25f
                        ).apply {
                            width = 150
                            height = 150
                            gravity = Gravity.CENTER
                        }
                        scaleType = ImageView.ScaleType.FIT_CENTER
                    }

                    // Загружаем изображение с помощью Glide с плейсхолдером на случай ошибки
                    Glide.with(this)
                        .load(product.thumbnail ?: "")
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(imageView)

                    row.addView(idTextView)
                    row.addView(titleTextView)
                    row.addView(priceTextView)
                    row.addView(imageView)

                    tableLayout.addView(row)
                }
            }
        })
    }

    private fun createTextView(text: String, weight: Float, gravity: Int): TextView {
        return TextView(this).apply {
            this.text = text
            this.setPadding(8, 8, 8, 8)
            this.gravity = gravity
            this.layoutParams = TableRow.LayoutParams(
                0, // Используем вес для ширины
                TableRow.LayoutParams.WRAP_CONTENT,
                weight
            )
        }
    }
}
