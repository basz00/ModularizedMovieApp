package com.baz.movie.extensions

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

fun <T> retrofitCallback(success: ((T?) -> Unit)? = null,
                         failure: ((String) -> Unit)? = null): Callback<T> {

    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            response?.let {
                if (it.isSuccessful) {
                    success?.invoke(it.body())
                } else {
                    failure?.invoke(processErrorResponse(it))
                }
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            val responseBody = t?.message ?: "Network unavailable"
            failure?.invoke(responseBody)
        }

        private fun processErrorResponse(it: Response<T>): String {
            return try {
                val error = it.errorBody()!!.string()
                val errorValue = if (error.isBlank()) it.message() else error
                errorValue
            } catch (exception: IOException) {
                Log.e("response_no_error_body", "Error no error body in response", exception)
                it.message()
            }
        }
    }
}