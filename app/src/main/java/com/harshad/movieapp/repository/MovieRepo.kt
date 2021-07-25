package com.harshad.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.data.remote.ApiClient
import com.harshad.movieapp.data.remote.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepo {

    val apiClient = Network.getInstance().create(ApiClient::class.java)

    fun fetchMovieShowListRepo(pageNo:String):LiveData<List<ResponseItem>>{
        var movieList = MutableLiveData<List<ResponseItem>>()
        var response = listOf<ResponseItem>()
        CoroutineScope(Dispatchers.IO).launch {
            response = apiClient.fetchMovieShowList(pageNo)
        }
        movieList.value = response
        Log.d("TAG","$movieList")
        return movieList
    }
}