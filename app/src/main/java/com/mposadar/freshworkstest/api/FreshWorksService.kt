package com.mposadar.freshworkstest.api

import com.mposadar.freshworkstest.api.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val TRENDING = "trending"
const val SEARCH = "search"

interface FreshWorksService {

    @GET(TRENDING)
    suspend fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("offset") offset: String,
    ): ApiResponse

    @GET(SEARCH)
    suspend fun search(
            @Query("api_key") apiKey: String,
            @Query("offset") offset: String,
            @Query("q") searchQuery: String,
    ): ApiResponse
}