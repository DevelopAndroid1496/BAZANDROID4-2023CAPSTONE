package com.example.themovieapp.common

data class BaseError(
    var status_message: String = "operation not performed",
    var status_code: Int = -1,
    var status_operation: Boolean = false,
    var exception: Exception? = null
)