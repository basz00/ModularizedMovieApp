package com.baz.movie.movies.data

import com.squareup.moshi.Json


internal class VideosResponse(@Json(name = "id") val id: Int,
                              @Json(name = "results") val results: List<Result>)

internal class Result(@Json(name = "id") val id: String,
                      @Json(name = "iso_639_1") val iso6391: String,
                      @Json(name = "iso_3166_1") val iso31661: String,
                      @Json(name = "key") val key: String,
                      @Json(name = "name") val name: String,
                      @Json(name = "site") val site: String,
                      @Json(name = "size") val size: Int,
                      @Json(name = "type") val type: String
)