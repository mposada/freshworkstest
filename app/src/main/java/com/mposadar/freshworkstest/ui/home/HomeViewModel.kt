package com.mposadar.freshworkstest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mposadar.freshworkstest.database.DatabaseRepository
import com.mposadar.freshworkstest.database.model.GifEntity
import com.mposadar.freshworkstest.model.Gif
import com.mposadar.freshworkstest.ui.home.paging.GifDataFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(private val databaseRepository: DatabaseRepository): ViewModel() {

    private val dataFactory = GifDataFactory(viewModelScope)
    private val pagedListConfig = PagedList.Config.Builder().setPageSize(GIF_PAGE_SIZE).setEnablePlaceholders(false)
    val gifResults: LiveData<PagedList<Gif?>> = LivePagedListBuilder(dataFactory, pagedListConfig.build()).build()
    var searchInput: MutableLiveData<String> = MutableLiveData()

    fun updateSearchInputValue(newValue: String) {
        searchInput.value = newValue
    }

    fun performSearch(queryText: String) {
        dataFactory.queryText = queryText
        gifResults.value?.dataSource?.invalidate()
    }

    fun addToFavorites(gif: Gif) = viewModelScope.launch(Dispatchers.IO) {
        gif.images.fixedHeightDownsampled.url?.let {
            databaseRepository.addFavoriteGif(
                GifEntity(id= gif.id, url = it, title = gif.title)
            )
        }
    }

    companion object {
        const val GIF_PAGE_SIZE = 50
    }
}