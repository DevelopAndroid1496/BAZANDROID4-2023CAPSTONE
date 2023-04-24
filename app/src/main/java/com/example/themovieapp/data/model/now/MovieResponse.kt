package com.example.themovieapp.data.model.now

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    @Expose
    var page:Int = 0,
    @SerializedName("results")
    @Expose
    var results:List<MovieRes> = emptyList(),
    @SerializedName("total_pages")
    @Expose
    var totalPages:Int = 0,
    @SerializedName("total_results")
    @Expose
    var totalResults:Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieResponse

        if (page != other.page) return false
        if (totalPages != other.totalPages) return false
        if (totalResults != other.totalResults) return false

        return true
    }

    override fun hashCode(): Int {
        var result = page
        result = 31 * result + totalPages
        result = 31 * result + totalResults
        return result
    }
}
