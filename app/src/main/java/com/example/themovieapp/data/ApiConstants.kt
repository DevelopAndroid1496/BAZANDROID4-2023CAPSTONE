package com.example.themovieapp.data

import com.example.themovieapp.BuildConfig

// Auth Key
const val API_KEY = "?api_key=${BuildConfig.AUTH_API_KEY}"

//HEADERS
const val HEADER_CONTENT_TYPE = "Content-Type: application/json;charset=utf-8"

//Endponits

const val VERSION = "3"
const val NOW_MOVIES = "${VERSION}/movie/now_playing${API_KEY}"