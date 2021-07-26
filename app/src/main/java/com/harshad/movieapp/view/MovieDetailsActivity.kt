package com.harshad.movieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.harshad.movieapp.R
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.infinite_post_item.view.*
import kotlinx.android.synthetic.main.movie_details_appbar.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        getMovieDetails()
        ivBackToHome.setOnClickListener {
            backToHome()
        }
    }

    private fun backToHome() {
        val intent = Intent(this@MovieDetailsActivity,MainActivity::class.java)
        startActivity(intent)
    }

    private fun getMovieDetails() {
        var MovieName = getIntent().getStringExtra("MovieName")
        var MoviePoster = getIntent().getStringExtra("MoviePoster")
        var MovieTime = getIntent().getStringExtra("MovieTime")
        var MovieDate = getIntent().getStringExtra("MovieDate")
        var MovieSummary = getIntent().getStringExtra("MovieSummary")
        Log.d("Poster", "$MovieName")
        setDataToViews(MovieName, MoviePoster, MovieTime, MovieDate, MovieSummary)
    }

    private fun setDataToViews(
        movieName: String?,
        moviePoster: String?,
        movieTime: String?,
        movieDate: String?,
        movieSummary: String?,
    ) {
        Glide.with(ivMoviePosterDetails).load(moviePoster).into(ivMoviePosterDetails)
        tvMovieNameDetails.text = movieName
        tvMovieTimeDetails.text = movieTime
        tvMovieDateDetails.text = movieTime
        tvMovieDateDetails.text = movieDate
        tvsummary.text = movieSummary
    }
}