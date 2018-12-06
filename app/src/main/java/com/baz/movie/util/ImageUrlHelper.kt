package com.baz.movie.util

internal class ImageUrlHelper {

    private companion object {

        private const val BASE_URL = "https://image.tmdb.org/t/p/"
        private const val PORTRAIT_WIDTH_CODE = "w185"
        private const val BANNER_WIDTH_CODE = "w342"
    }

    fun generatePosterImageUrl(imageUrl: String) = "$BASE_URL$PORTRAIT_WIDTH_CODE$imageUrl"

    fun generateBannerImageUrl(imageUrl: String) = "$BASE_URL$BANNER_WIDTH_CODE$imageUrl"
}