package com.example.themovieapp.data.model.latest

import com.example.themovieapp.data.model.now.Gender
import com.google.gson.annotations.SerializedName

data class LatestMovieResponse (
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: Any? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("genres")
    val genres: List<Gender>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("imdb_id")
    val imdbID: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: Any? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<Any?>,
    @SerializedName("production_countries")
    val productionCountries: List<Any?>,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: Long,
    @SerializedName("runtime")
    val runtime: Long,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long
)

