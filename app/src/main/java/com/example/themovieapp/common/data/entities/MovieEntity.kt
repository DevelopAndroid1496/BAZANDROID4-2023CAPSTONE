package com.example.themovieapp.common.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themovieapp.data.model.now.Gender
import com.example.themovieapp.domain.model.Movie

@Entity(tableName = "movie_now_table")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo(name = "idMovie") var idMovie: Int = 0,
    @ColumnInfo(name = "adult") var adult: Boolean = false,
    @ColumnInfo(name = "generos") var genres : List<Int> = emptyList(),
    @ColumnInfo(name = "backdrop_path") var backdropPath: String = "",
    @ColumnInfo(name = "overview") var overview: String = "",
    @ColumnInfo(name = "poster_path") var posterPath: String? = "",
    @ColumnInfo(name = "release_date") var releaseDate: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "vote_average") var voteAverage: Double = 0.0,
    @ColumnInfo(name = "vote_count") var voteCount: Int = 0
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

