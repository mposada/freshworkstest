package com.mposadar.freshworkstest.ui.home.paging

import androidx.paging.PageKeyedDataSource
import com.mposadar.freshworkstest.api.Repository
import com.mposadar.freshworkstest.model.Gif
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GifDataSource(private val scope: CoroutineScope, private val queryText: String): PageKeyedDataSource<String, Gif>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Gif>
    ) {
        scope.launch {
            val response = if (queryText == "") Repository.getTrending() else Repository.search(queryText)
            if (response?.isSuccess == true) {
                response.success?.let {
                    val nextPageKey = it.pagination.count + it.pagination.offset
                    callback.onResult(it.data, null, nextPageKey.toString())
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Gif>) {}

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Gif>) {
        scope.launch {
            val response = if (queryText == "") Repository.getTrending(params.key) else Repository.search(queryText, params.key)
            if (response?.isSuccess == true) {
                response.success?.let {
                    val nextPageKey = it.pagination.count + it.pagination.offset
                    callback.onResult(it.data, nextPageKey.toString())
                }
            }
        }
    }

}