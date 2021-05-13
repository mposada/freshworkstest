package com.mposadar.freshworkstest.database

import com.mposadar.freshworkstest.database.model.GifEntity
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val gifDao: GifDao) {

    fun getFavoriteGifs(): Flow<List<GifEntity>> = gifDao.getGifs()

    suspend fun addFavoriteGif(gifEntity: GifEntity) {
        gifDao.insertGif(gifEntity)
    }

    suspend fun deleteFavorite(gifEntity: GifEntity) {
        gifDao.deleteGif(gifEntity)
    }
}