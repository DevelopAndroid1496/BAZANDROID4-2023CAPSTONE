package com.example.themovieapp.data.model.genders

import com.example.local.db.entities.GenderMovieEntity
import com.example.themovieapp.domain.model.GenderDom
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieGenderResponse(
    @SerializedName("genres")
    @Expose
    var listGenders : List<GenderMovie> = emptyList()
)

fun MovieGenderResponse.toDatabaseGenders() = mutableListOf<com.example.local.db.entities.GenderMovieEntity>().apply {
    listGenders.forEach {
        add(com.example.local.db.entities.GenderMovieEntity(it.id, it.name))
    }
}

fun MovieGenderResponse.toDomain() = mutableListOf<GenderDom>().apply {
    listGenders.forEach {
        add(GenderDom(it.id,it.name))
    }
}