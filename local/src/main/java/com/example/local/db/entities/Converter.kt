package com.example.local.db.entities

import androidx.room.TypeConverter
import com.example.local.db.model.GenderMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun objectToJson(value: Any?) = Gson().toJson(value)

    @TypeConverter
    fun stringToListGenere(string: String?): List<Int>? {
        return Gson().fromJson(
            string,
            object : TypeToken<List<Int>?>() {}.type
        )
    }

    @TypeConverter
    fun stringToListGenereAndName(string: String?): List<GenderMovie>? {
        return Gson().fromJson(
            string,
            object : TypeToken<List<GenderMovie>?>() {}.type
        )
    }
}