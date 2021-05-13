package com.mposadar.freshworkstest.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mposadar.freshworkstest.database.DatabaseRepository
import com.mposadar.freshworkstest.database.model.GifEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(private val databaseRepository: DatabaseRepository): ViewModel() {

    val favoriteGifs: LiveData<List<GifEntity>> = databaseRepository.getFavoriteGifs().asLiveData()

    fun deleteFavorite(gifEntity: GifEntity) = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.deleteFavorite(gifEntity)
    }

}

