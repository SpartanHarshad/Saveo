package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class WebChannel(
    @SerializedName("country")
    var country: Country? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null
)