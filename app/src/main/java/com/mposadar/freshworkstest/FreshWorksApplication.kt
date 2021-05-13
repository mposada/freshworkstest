package com.mposadar.freshworkstest

import android.app.Application
import com.mposadar.freshworkstest.database.AppDatabase
import com.mposadar.freshworkstest.database.DatabaseRepository

class FreshWorksApplication: Application() {

    companion object {

        private lateinit var instance: FreshWorksApplication

        private val database: AppDatabase by lazy {
            AppDatabase.buildDatabase(instance)
        }

        val databaseRepository: DatabaseRepository by lazy {
            DatabaseRepository(database.gifDao())
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}