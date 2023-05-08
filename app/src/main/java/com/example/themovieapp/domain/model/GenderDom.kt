package com.example.themovieapp.domain.model

import com.example.local.db.entities.GenderMovieEntity
import com.example.remote.di.model.genders.GenderMovie
import com.example.remote.di.model.genders.MovieGenderResponse

data class GenderDom(
    var id: Int,
    var name: String
)

fun com.example.remote.di.model.genders.GenderMovie.toDatabaseGenders() = com.example.local.db.entities.GenderMovieEntity(
    id,
    name
)

fun GenderMovieEntity.toDomain() = GenderDom(
    id,
    name
)


fun MovieGenderResponse.toDatabaseGenders() = mutableListOf<GenderMovieEntity>().apply {
    listGenders.forEach {
        add(com.example.local.db.entities.GenderMovieEntity(it.id, it.name))
    }
}

fun MovieGenderResponse.toDomain() = mutableListOf<GenderDom>().apply {
    listGenders.forEach {
        add(GenderDom(it.id,it.name))
    }
}


