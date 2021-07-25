package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    var average: Double? = null
)