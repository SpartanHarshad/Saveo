package com.harshad.movieapp.clicklistener

import com.harshad.movieapp.data.model.ResponseItem

interface PostClickListener {
    fun onPosterClickListener(responseItem: ResponseItem)
}