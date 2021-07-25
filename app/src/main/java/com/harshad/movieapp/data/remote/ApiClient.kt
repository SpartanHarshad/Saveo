package com.harshad.movieapp.data.remote


import com.harshad.movieapp.data.model.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    companion object{
        val BASE_URL = "https://api.tvmaze.com/"
    }

    @GET("shows")
    suspend fun fetchMovieShowList(
        @Query("page") pageNo :String
    ): List<ResponseItem>
}