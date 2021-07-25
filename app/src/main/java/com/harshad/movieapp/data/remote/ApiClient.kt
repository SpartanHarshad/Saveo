package com.harshad.movieapp.data.remote

import androidx.lifecycle.LiveData
import com.harshad.movieapp.data.model.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    companion object{
        val BASE_URL = "https://api.tvmaze.com/"
    }

    @GET("shows")
    suspend fun fetchMovieShowList(
        @Query("page") topic :String
    ):List<ResponseItem>
}