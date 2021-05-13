package com.mposadar.freshworkstest.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> LiveData<T>.debounce(duration: Long = 1000L, coroutineScope: CoroutineScope) = MediatorLiveData<T>().also { mediatorLiveData ->

    val source = this
    var job: Job? = null

    mediatorLiveData.addSource(source) {
        job?.cancel()
        job = coroutineScope.launch {
            delay(duration)
            mediatorLiveData.value = source.value
        }
    }
}