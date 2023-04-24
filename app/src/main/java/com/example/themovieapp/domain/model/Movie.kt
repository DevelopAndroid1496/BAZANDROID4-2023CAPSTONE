package com.example.themovieapp.domain.model

import com.example.themovieapp.common.data.entities.MovieEntity
import com.example.themovieapp.data.model.now.MovieRes

data class Movie(var adult: Boolean = false,
                 var backdropPath: String = "",
                 var id: String = "",
                 var overview: String = "",
                 var posterPath: String? = "",
                 var releaseDate: String = "",
                 var title: String = "",
                 var voteAverage: Double = 0.0,
                 var voteCount: Int = 0,)


fun MovieRes.toDomain() =
    Movie(
        adult,
        backdropPath,
        id,
        overview,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )

fun MovieEntity.toDomain() =
    Movie(
        adult,
        backdropPath,
        id,
        overview,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )