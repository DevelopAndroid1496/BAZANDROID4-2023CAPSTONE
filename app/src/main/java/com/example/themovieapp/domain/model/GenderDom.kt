package com.example.themovieapp.domain.model

import com.example.themovieapp.common.data.entities.GenderMovieEntity
import com.example.themovieapp.data.model.genders.GenderMovie

data class GenderDom(
    var id: Int,
    var name: String
)

fun GenderMovie.toDatabaseGenders() = GenderMovieEntity(
    id,
    name
)


