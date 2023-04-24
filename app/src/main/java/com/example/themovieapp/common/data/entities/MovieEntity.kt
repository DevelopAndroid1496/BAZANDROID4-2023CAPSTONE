package com.example.themovieapp.common.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themovieapp.domain.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idMovie") var idMovie: Int = 0,
    @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo("adult") var adult: Boolean = false,
    @ColumnInfo("backdrop_path") var backdropPath: String = "",
    @ColumnInfo("overview") var overview: String = "",
    @ColumnInfo("poster_path") var posterPath: String? = "",
    @ColumnInfo("release_date") var releaseDate: String = "",
    @ColumnInfo("title") var title: String = "",
    @ColumnInfo("vote_average") var voteAverage: Double = 0.0,
    @ColumnInfo("vote_count") var voteCount: Int = 0,
)

fun Movie.toDatabaseNow() = MovieEntity(
    idMovie = 0,
    id,
    adult,
    backdropPath,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    voteCount
)

