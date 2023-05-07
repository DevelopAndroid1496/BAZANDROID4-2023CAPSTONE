package com.example.themovieapp.domain.model

import android.os.Parcelable
import com.example.local.db.entities.MovieEntity
import com.example.local.db.entities.TopRatedMovieEntity
import com.example.themovieapp.data.model.now.MovieRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(var adult: Boolean = false,
                 var backdropPath: String = "",
                 var id: String = "",
                 var gender: List<Int>,
                 var overview: String = "",
                 var posterPath: String? = "",
                 var releaseDate: String = "",
                 var title: String = "",
                 var voteAverage: Double = 0.0,
                 var voteCount: Int = 0,):Parcelable


fun MovieRes.toDomain() =
    Movie(
        adult,
        backdropPath,
        id,
        genderList,
        overview,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )

fun com.example.local.db.entities.MovieEntity.toDomain() =
    Movie(
        adult,
        backdropPath,
        id,
        genres,
        overview,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )

fun com.example.local.db.entities.TopRatedMovieEntity.toDomain() =
    Movie(
        adult,
        backdropPath,
        id,
        genres,
        overview,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )

fun Movie.toDatabaseNow() = MovieEntity(
    id,
    idMovie = 0,
    adult,
    gender,
    backdropPath,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    voteCount
)

fun Movie.toDatabaseTop() = MovieEntity(
    id,
    idMovie = 0,
    adult,
    gender,
    backdropPath,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    voteCount
)