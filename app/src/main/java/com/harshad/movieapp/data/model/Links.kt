package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("previousepisode")
    var previousepisode: Previousepisode? = null,
    @SerializedName("self")
    var self: Self? = null
)