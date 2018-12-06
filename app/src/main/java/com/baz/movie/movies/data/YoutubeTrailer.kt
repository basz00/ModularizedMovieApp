package com.baz.movie.movies.data

import com.squareup.moshi.Json

internal data class YoutubeTrailer(@Json(name = "id") val id: String,
                                   @Json(name = "key") val key: String,
                                   @Json(name = "name") val name: String)