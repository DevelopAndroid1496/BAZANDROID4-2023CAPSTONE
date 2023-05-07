package com.example.themovieapp.data.model.genders

import com.example.themovieapp.common.data.entities.GenderMovieEntity
import com.example.themovieapp.domain.model.GenderDom
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieGenderResponse(
    @SerializedName("genres")
    @Expose
    var listGenders : List<GenderMovie> = emptyList()
)

fun MovieGenderResponse.toDatabaseGenders() = mutableListOf<GenderMovieEntity>().apply {
    listGenders.forEach {
        add(GenderMovieEntity(it.id,it.name))
    }
}

fun MovieGenderResponse.toDomain() = mutableListOf<GenderDom>().apply {
    listGenders.forEach {
        add(GenderDom(it.id,it.name))
    }
}