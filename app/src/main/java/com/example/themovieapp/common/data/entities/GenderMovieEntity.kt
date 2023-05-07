package com.example.themovieapp.common.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themovieapp.data.model.genders.GenderMovie
import com.example.themovieapp.data.model.now.Gender
import com.example.themovieapp.domain.model.GenderDom

@Entity(tableName = "gender_movie_table")
data class GenderMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String
)

fun GenderMovieEntity.toDomain() = GenderDom(
    id,
    name
)
