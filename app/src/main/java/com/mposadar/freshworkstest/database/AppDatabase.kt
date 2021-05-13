package com.mposadar.freshworkstest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mposadar.freshworkstest.database.model.GifEntity

const val DATABASE_VERSION = 1

@Database(
    entities = [GifEntity::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "freshworkstest_db"
        private var INSTANCE: AppDatabase? = null

        fun buildDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun gifDao() : GifDao
}