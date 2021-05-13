package com.mposadar.freshworkstest.api

import com.mposadar.freshworkstest.BuildConfig
import com.mposadar.freshworkstest.api.model.ApiResponse
import com.mposadar.freshworkstest.api.model.Result
import com.mposadar.freshworkstest.model.Gif

object Repository {

    private val client = ServiceGenerator.createService(FreshWorksService::class.java)

    suspend fun getTrending(offset: String = "0"): Result<ApiResponse>? {
        return try {
            val result = client.getTrending(BuildConfig.API_KEY, offset) // TODO: add as an interceptor
            Result(result)
        } catch (error: Throwable) {
            Result(null, error = error.localizedMessage)
        }
    }

    suspend fun search(queryText: String, offset: String = "0"): Result<ApiResponse>? {
        return try {
            val result = client.search(BuildConfig.API_KEY, offset, queryText)
            Result(result)
        } catch (error: Throwable) {
            Result(null, error = error.localizedMessage)
        }
    }

}