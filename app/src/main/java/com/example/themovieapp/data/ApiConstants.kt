package com.example.themovieapp.data

import com.example.themovieapp.BuildConfig

// Auth Key
const val API_KEY = "?api_key=${BuildConfig.AUTH_API_KEY}"

//HEADERS
const val HEADER_CONTENT_TYPE = "Content-Type: application/json;charset=utf-8"

//Endponits

const val VERSION = "3"
const val PATH_IMAGES = "t/p/w500"
const val NOW_MOVIES = "${VERSION}/movie/now_playing${API_KEY}"
const val LATEST_MOVIE = "${VERSION}/movie/latest${API_KEY}"
const val TOP_RATED = "${VERSION}/movie/top_rated${API_KEY}"
const val GENDERS = "${VERSION}/genre/movie/list${API_KEY}"