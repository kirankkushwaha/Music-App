package com.example.firstktapp



import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
    @Headers("x-rapidapi-key: 115e95b6d8msh7a3339205cd5edbp1b59fcjsn7a23eccfd999",
        "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MyData>

}