package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("days")
    var days: List<String>? = null,
    @SerializedName("time")
    var time: String? = null
)