package com.example.remote.di.model.latest

import com.google.gson.annotations.SerializedName

data class SpokenLanguage (
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso639_1: String,
    @SerializedName("name")
    val name: String
)
