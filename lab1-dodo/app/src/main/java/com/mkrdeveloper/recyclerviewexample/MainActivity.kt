package com.mkrdeveloper.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var imageId: Array<Int>
    private lateinit var names: Array<String>
    private lateinit var ingredients: Array<String>

    private lateinit var recView : RecyclerView
    private lateinit var itemArrayList: ArrayList<Pizza>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
        )

        names = arrayOf(
            "Баварская",
            "Вау! Кебаб",
            "Пепперони с грибами",
            "Ветчина и огурчики",
            "Пицца Жюльен"
        )

        ingredients = arrayOf(
            "Острые колбаски чоризо, маринованные огурчики, красный лук, томаты, горчичный соус, моцарелла, фирменный томатный соус",
            "Мясо говядины, соус ранч, сыр моцарелла, сладкий перец, томаты, красный лук и фирменный томатный соус",
            "Пикантная пепперони, моцарелла, шампиньоны, соус альфредо",
            "Соус ранч, моцарелла, ветчина из цыпленка, маринованные огурчики, красный лук",
            "Цыпленок, шампиньоны, ароматный грибной соус, лук, сухой чеснок, моцарелла, смесь сыров чеддер и пармезан, фирменный соус альфредо"
        )

        recView = findViewById(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)
        recView.setHasFixedSize(true)

        itemArrayList = arrayListOf()

        getData()

        val adapter = RecAadapter(itemArrayList)
        recView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val searchList = ArrayList<Pizza>()

                if (newText != null){
                    for (i in itemArrayList){
                        if (i.name.lowercase(Locale.ROOT).contains(newText)){
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(this@MainActivity, " No Data", Toast.LENGTH_SHORT).show()
                    }else{

                        adapter.onApplySearch(searchList)
                    }
                }

                return true
            }

        })
    }

    private fun getData() {

        for (i in imageId.indices){
            val pizza = Pizza(imageId[i], names[i], ingredients[i])
            itemArrayList.add(pizza)
        }
    }
}