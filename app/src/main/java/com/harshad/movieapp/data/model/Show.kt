package com.harshad.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("averageRuntime")
    var averageRuntime: Int? = null,
    @SerializedName("dvdCountry")
    var dvdCountry: Any? = null,
    @SerializedName("externals")
    var externals: Externals? = null,
    @SerializedName("genres")
    var genres: List<String>? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("image")
    var image: Image? = null,
    @SerializedName("language")
    var language: String? = null,
    @SerializedName("_links")
    var links: Links? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("network")
    var network: Any? = null,
    @SerializedName("officialSite")
    var officialSite: String? = null,
    @SerializedName("premiered")
    var premiered: String? = null,
    @SerializedName("rating")
    var rating: Rating? = null,
    @SerializedName("runtime")
    var runtime: Int? = null,
    @SerializedName("schedule")
    var schedule: Schedule? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("summary")
    var summary: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("updated")
    var updated: Int? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("webChannel")
    var webChannel: WebChannel? = null,
    @SerializedName("weight")
    var weight: Int? = null
)