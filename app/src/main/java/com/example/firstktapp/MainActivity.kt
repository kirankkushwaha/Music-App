package com.example.firstktapp



import MyAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
myRecyclerView=findViewById(R.id.recyclerView)


        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getData(query = "green day")
        retrofitData.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                // if the API call is a success then this method is executed
                val dataList = response.body()?.data!! // Safely access 'data'
                /*val textView = findViewById<TextView>(R.id.helloText)
                textView.text = dataList?.toString() ?: "No data available" // Handle null safely*/

                myAdapter=MyAdapter(context = this@MainActivity, dataList)
                myRecyclerView.adapter= myAdapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

                Log.d("TAG", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                // if the API call is a failure then this method is executed
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })



    }
}