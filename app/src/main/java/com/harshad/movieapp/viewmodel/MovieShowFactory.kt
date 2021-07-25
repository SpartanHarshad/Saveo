package com.harshad.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshad.movieapp.repository.MovieRepo

class MovieShowFactory(val movieRepo: MovieRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepo) as T
    }
}