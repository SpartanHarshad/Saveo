package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class Externals(
    @SerializedName("imdb")
    var imdb: String? = null,
    @SerializedName("thetvdb")
    var thetvdb: Int? = null,
    @SerializedName("tvrage")
    var tvrage: Any? = null
)