package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("timezone")
    var timezone: String? = null
)