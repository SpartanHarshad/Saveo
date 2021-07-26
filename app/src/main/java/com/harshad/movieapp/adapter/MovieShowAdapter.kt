package com.harshad.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshad.movieapp.R
import com.harshad.movieapp.data.model.ResponseItem
import kotlinx.android.synthetic.main.movie_poster_item.view.*

class MovieShowAdapter(val moviePosterList: List<ResponseItem>) :
    RecyclerView.Adapter<MoviePosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_poster_item, parent, false)
        return MoviePosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val responseItem: ResponseItem = moviePosterList[position]
        holder.setImagePoster(responseItem)
    }

    override fun getItemCount(): Int {
        return moviePosterList.size
    }
}

class MoviePosterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun setImagePoster(responseItem: ResponseItem) {
        view.apply {
            Glide.with(ivMoviePoster).load(responseItem?.image?.original).into(ivMoviePoster)
        }
    }
}