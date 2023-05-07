package com.example.themovieapp.domain.model

import com.example.local.db.entities.GenderMovieEntity
import com.example.themovieapp.data.model.genders.GenderMovie

data class GenderDom(
    var id: Int,
    var name: String
)

fun GenderMovie.toDatabaseGenders() = com.example.local.db.entities.GenderMovieEntity(
    id,
    name
)

fun GenderMovieEntity.toDomain() = GenderDom(
    id,
    name
)


