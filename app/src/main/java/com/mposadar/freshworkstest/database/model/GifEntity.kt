package com.mposadar.freshworkstest.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "gifs")
data class GifEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val title: String)
