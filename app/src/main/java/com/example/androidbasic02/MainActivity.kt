package com.example.androidbasic02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    var nameArray = arrayOf("하나", "둘", "셋")
    var adapter: ArrayAdapter<String>? = null

    val productArray = arrayOf(
        ProductModel("김치찌개", 5000),
        ProductModel("삼겹살", 12000),
        ProductModel("계란찜", 3000)
    )

    var numberList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, nameArray)
        nameOption.adapter = adapter
        nameOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                greetText.setText("${nameArray[position]}님, 안녕하세요")
            }
        }

        val productAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productArray)
        productOption.adapter = productAdapter
        productOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                with(productArray[position]) {
                    priceText.setText("${name}의 가격은,  ${price}입니다.")
                }
            }
        }

        val numberAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, numberList)
        numberOption.adapter = numberAdapter
        numberOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                notifyText.setText("${numberList[position]}번을 선택했습니다")
            }
        }

        addButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                numberList.add(Random.nextInt(0..100))
                numberAdapter.notifyDataSetChanged()
            }
        })

        removeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (numberList.isNotEmpty()) {
                    numberList.removeAt(0)
                    numberAdapter.notifyDataSetChanged()
                }
            }
        })

    }
}
