package com.mposadar.freshworkstest.api

import com.mposadar.freshworkstest.api.model.ApiResponse
import com.mposadar.freshworkstest.api.model.Result
import com.mposadar.freshworkstest.model.Gif
import junit.framework.TestCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RepositoryTest : TestCase() {

    @Test
    fun testGetTrending() = runBlocking {
        val deferred: Deferred<Result<ApiResponse>?> = async { Repository.getTrending() }
        val response = deferred.await()
        if (response?.isSuccess == true) {
            assertNotNull(response.success)
        } else {
            assertNotNull(response?.error)
        }
    }

    @Test
    fun testGetTrendingWithoutAwait() = runBlocking {
        val response = Repository.getTrending()
        if (response?.isSuccess == true) {
            assertNotNull(response.success?.data)
        } else {
            assertNull(response?.error)
        }
    }
}