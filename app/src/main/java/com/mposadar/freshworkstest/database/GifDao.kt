package com.mposadar.freshworkstest.database

import androidx.room.*
import com.mposadar.freshworkstest.database.model.GifEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {

    @Query("SELECT * FROM gifs")
    fun getGifs(): Flow<List<GifEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGif(gifEntity: GifEntity)

    @Delete
    fun deleteGif(gifEntity: GifEntity)
}