package com.harshad.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshad.movieapp.R
import com.harshad.movieapp.clicklistener.PostClickListener
import com.harshad.movieapp.data.model.ResponseItem
import kotlinx.android.synthetic.main.infinite_post_item.view.*


class InfiniteMoviePosterAdapter(
    var moviePosterList: List<ResponseItem>,
    val postClickListener: PostClickListener,
) : RecyclerView.Adapter<InfiniteMoviePosterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteMoviePosterHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.infinite_post_item, parent, false)
        return InfiniteMoviePosterHolder(view, postClickListener)
    }

    override fun onBindViewHolder(holder: InfiniteMoviePosterHolder, position: Int) {
        val responseItem: ResponseItem = moviePosterList[position]
        holder.setMoviePoster(responseItem)
    }

    override fun getItemCount(): Int {
        return moviePosterList.size
    }

}

class InfiniteMoviePosterHolder(val view: View, val postClickListener: PostClickListener) :
    RecyclerView.ViewHolder(view) {

    fun setMoviePoster(responseItem: ResponseItem) {
        view.apply {
            Glide.with(ivMoviePosterInfinite).load(responseItem?.image?.original)
                .into(ivMoviePosterInfinite)
        }
        view.apply {
            clyMoviePoster.setOnClickListener {
                postClickListener.onPosterClickListener(responseItem)
            }
        }
    }
}
