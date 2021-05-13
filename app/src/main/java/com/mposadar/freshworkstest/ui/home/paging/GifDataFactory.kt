package com.mposadar.freshworkstest.ui.home.paging

import androidx.paging.DataSource
import com.mposadar.freshworkstest.model.Gif
import kotlinx.coroutines.CoroutineScope

class GifDataFactory(private val scope: CoroutineScope) : DataSource.Factory<String?, Gif?>() {

    var queryText = ""

    override fun create(): DataSource<String?, Gif?> {
        return GifDataSource(scope, queryText)
    }
}