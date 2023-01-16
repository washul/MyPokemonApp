package com.wsl.data.utils

import android.util.Log
import com.google.gson.Gson
import com.wsl.domain.pokemon.model.ServerErrorResponse
import org.json.JSONObject
import retrofit2.Response
import com.wsl.utils.Failure
import com.wsl.utils.Result

inline fun <T> Request(
    call: () -> Response<T>,
    default: T
): Result<Failure, T> {
    return try {
        val response = call.invoke()
        when (response.isSuccessful) {
            true -> {Result.Success(response.body() ?: default)}
            false -> {
                val gson = Gson()
                val responseString = response.errorBody()?.string().orEmpty()
                val jsonObject = JSONObject(responseString)
                when {
                    jsonObject.has("code") && !jsonObject.getString("code").equals("200") -> {
                        val processError = gson.fromJson(responseString, ServerErrorResponse::class.java)
                        Result.Failure(
                            Failure.CustomError(
                                errorMessage = processError.status
                            )
                        )
                    }
                    else -> {
                        val error = gson.fromJson(responseString, Error::class.java)
                        Result.Failure(
                            Failure.CustomError("Something happens!: ${error.message}")
                        )
                    }
                }
            }
        }
    } catch (exception: Throwable) {
        Log.e("Network", exception.toString())
        Result.Failure(
            Failure.ServerError(exception)
        )
    }
}