package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class ResponseItem(
    @SerializedName("score")
    var score: Double? = null,
    @SerializedName("show")
    var show: Show? = null
)