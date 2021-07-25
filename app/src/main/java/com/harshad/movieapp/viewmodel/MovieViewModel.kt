package com.harshad.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.repository.MovieRepo

class MovieViewModel(val movieRepo: MovieRepo ):ViewModel() {

    fun fetchMovieShowList(pageNo:String):LiveData<List<ResponseItem>>{
        return movieRepo.fetchMovieShowListRepo(pageNo)
    }
}