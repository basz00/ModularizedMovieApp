package com.baz.movie.movies.data

import com.squareup.moshi.Json


internal data class CreditsResponse(@Json(name = "id") val id: Int,
                                    @Json(name = "cast") val cast: List<Cast>,
                                    @Json(name = "crew") val crew: List<Crew>)

internal data class Cast(@Json(name = "cast_id") val castId: Int,
                         @Json(name = "character") val character: String,
                         @Json(name = "credit_id") val creditId: String,
                         @Json(name = "gender") val gender: Int,
                         @Json(name = "id") val id: Int,
                         @Json(name = "name") val name: String,
                         @Json(name = "order") val order: Int,
                         @Json(name = "profile_path") val profilePath: String)

internal data class Crew(
        @Json(name = "credit_id") val creditId: String,
        @Json(name = "department") val department: String,
        @Json(name = "gender") val gender: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "job") val job: String,
        @Json(name = "name") val name: String,
        @Json(name = "profile_path") val profilePath: String)