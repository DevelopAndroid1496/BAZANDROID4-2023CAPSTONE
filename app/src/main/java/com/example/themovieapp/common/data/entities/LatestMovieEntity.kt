package com.example.themovieapp.common.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.domain.model.MovieLatest

@Entity(tableName = "movie_latest_table")
data class LatestMovieEntity(
    @PrimaryKey
    @ColumnInfo("id") var id: Int,
    @ColumnInfo("title") var title: String = "",
    @ColumnInfo("adult") var adult: Boolean,
    @ColumnInfo("backdrop_path") var backdrop_path: String = "",
    @ColumnInfo("budget") var budget: Int,
    @ColumnInfo("homepage") var homepage: String = "",
    @ColumnInfo("imdb_id") var imdb_id: String= "",
    @ColumnInfo("original_language") var original_language: String = "",
    @ColumnInfo("original_title") var original_title: String = "",
    @ColumnInfo("overview") var overview: String = "",
    @ColumnInfo("popularity") var popularity: Double,
    @ColumnInfo("poster_path") var poster_path: String = "",
    @ColumnInfo("release_date") var release_date: String = "",
    @ColumnInfo("revenue") var revenue: Int,
    @ColumnInfo("runtime") var runtime: Int,
    @ColumnInfo("status") var status: String = "",
    @ColumnInfo("tagline") var tagline: String = "",
    @ColumnInfo("video") var video: Boolean,
    @ColumnInfo("vote_average") var vote_average: Int,
    @ColumnInfo("vote_count") var vote_count: Int,

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




