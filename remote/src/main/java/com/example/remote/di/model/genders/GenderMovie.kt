package com.example.remote.di.model.genders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenderMovie(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String
)

fun GenderMovie.toDomain() = GenderMovie(
    id,
    name
)

