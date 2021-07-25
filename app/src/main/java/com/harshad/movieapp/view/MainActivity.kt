package com.harshad.movieapp.view

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.harshad.movieapp.R
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.viewmodel.MovieApplication
import com.harshad.movieapp.viewmodel.MovieShowFactory
import com.harshad.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewModel
    var movieShowList = MutableLiveData<List<ResponseItem>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        btnCallApi.setOnClickListener {
            movieShowList = viewModel.fetchMovieShowList("1") as MutableLiveData<List<ResponseItem>>
            Log.d("Response","$movieShowList")
        }
    }

    private fun initViewModel() {
        val movieApplication = application as MovieApplication
        val repo = movieApplication.movieRepo
        val movieFactory = MovieShowFactory(repo)
        viewModel = ViewModelProviders.of(this, movieFactory)
            .get(MovieViewModel::class.java)
    }
}