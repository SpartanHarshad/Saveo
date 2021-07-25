package com.harshad.movieapp.repository


import android.util.Log
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.data.remote.ApiClient
import com.harshad.movieapp.data.remote.Network
import com.harshad.movieapp.data.remote.Resource
import com.harshad.movieapp.data.remote.ResponseHandler


class MovieRepo {

    val apiClient = Network.getInstance().create(ApiClient::class.java)
    val responseHandler = ResponseHandler()

    suspend fun fetchMovieShowListRepo(pageNo: String): Resource<List<ResponseItem>> {
        var response = apiClient.fetchMovieShowList(pageNo)
        var successResponse = responseHandler.handleSuccess(response)
        Log.d("Repo", "$successResponse")
        return successResponse
    }
}