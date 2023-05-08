package com.example.remote.di.service


class ApiConstants{
    companion object {
        const val BASE_URL_IMAGES = "https://image.tmdb.org/"
        const val BASE_URL = "https://api.themoviedb.org/"


        // Auth Key
        const val API_KEY = "?api_key=5e11b2efdbf9db48f1ccfd64bb9e0542"

        //HEADERS
        const val HEADER_CONTENT_TYPE = "Content-Type: application/json;charset=utf-8"

//Endponits

        const val VERSION = "3"
        const val PATH_IMAGES = "t/p/w500"
        const val NOW_MOVIES = "$VERSION/movie/now_playing$API_KEY"
        const val LATEST_MOVIE = "$VERSION/movie/latest$API_KEY"
        const val TOP_RATED = "$VERSION/movie/top_rated$API_KEY"
        const val GENDERS = "$VERSION/genre/movie/list$API_KEY"
    }
}

