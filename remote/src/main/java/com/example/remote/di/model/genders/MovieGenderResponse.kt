package com.example.remote.di.model.genders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieGenderResponse(
    @SerializedName("genres")
    @Expose
    var listGenders : List<GenderMovie> = emptyList()
)

