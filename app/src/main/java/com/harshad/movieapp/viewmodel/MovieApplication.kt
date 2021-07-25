package com.harshad.movieapp.viewmodel

import android.app.Application
import com.harshad.movieapp.repository.MovieRepo

class MovieApplication:Application() {

    val movieRepo by lazy {
        MovieRepo()
    }
}