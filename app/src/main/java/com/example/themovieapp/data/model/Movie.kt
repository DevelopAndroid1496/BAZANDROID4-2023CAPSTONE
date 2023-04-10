package com.example.themovieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")
    @Expose
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String = "",
    @SerializedName("id")
    @Expose
    var id: String = "",
    @SerializedName("overview")
    @Expose
    var overview: String = "",
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = "",
    @SerializedName("release_date")
    @Expose
    var releaseDate: String = "",
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int = 0
)
