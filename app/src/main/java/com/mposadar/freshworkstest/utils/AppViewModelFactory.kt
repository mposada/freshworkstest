package com.mposadar.freshworkstest.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mposadar.freshworkstest.database.DatabaseRepository

class AppViewModelFactory(private val databaseRepository: DatabaseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DatabaseRepository::class.java).newInstance(databaseRepository)
    }
}