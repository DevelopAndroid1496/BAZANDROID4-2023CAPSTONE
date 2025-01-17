package com.example.themovieapp.domain.model

import android.os.Parcelable
import com.example.local.db.entities.LatestMovieEntity
import com.example.remote.di.model.latest.LatestMovieResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieLatest(
    var id: Int = 0,
    var title: String = "",
    var adult: Boolean,
    var backdropPath: String = "",
    var budget: Int,
    var homepage: String = "",
    var imdb_id: String?= "",
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double,
    var poster_path: String = "",
    var release_date: String = "",
    var revenue: Int,
    var runtime: Int,
    var status: String = "",
    var tagline: String = "",
    var video: Boolean,
    var vote_average: Int,
    var vote_count: Int,
    ) : Parcelable


fun com.example.remote.di.model.latest.LatestMovieResponse.toDomain() = backdropPath?.let {
    MovieLatest(
        id,
        title,
        adult,
        it,
        budget,
        homepage,
        imdbID,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath!!,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        video,
        voteAverage,
        voteCount
    )
}

fun com.example.local.db.entities.LatestMovieEntity.toDomain() = MovieLatest(
    id,
    title,
    adult,
    backdrop_path,
    budget,
    homepage,
    imdb_id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    revenue,
    runtime,
    status,
    tagline,
    video,
    vote_average,
    vote_count
)

fun MovieLatest.toDatabaseLatest() = imdb_id?.let {
    LatestMovieEntity(
        id,
        title,
        adult,
        backdropPath,
        budget,
        homepage,
        it,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        revenue,
        runtime,
        status,
        tagline,
        video,
        vote_average,
        vote_count
    )
}
