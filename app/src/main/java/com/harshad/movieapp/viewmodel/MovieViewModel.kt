package com.harshad.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.repository.MovieRepo
import kotlinx.coroutines.Dispatchers

class MovieViewModel(val movieRepo: MovieRepo) : ViewModel() {

    fun fetchMovieShowList(pageNo: String): LiveData<List<ResponseItem>> {
        return liveData(Dispatchers.IO) {
            val responseList = movieRepo.fetchMovieShowListRepo(pageNo)
            emit(responseList.data!!)
        }
    }
}