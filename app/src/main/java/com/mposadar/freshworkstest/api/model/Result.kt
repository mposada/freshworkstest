package com.mposadar.freshworkstest.api.model

data class Result<T>(val success: T?, val error: String? = null) {
    val isSuccess: Boolean
        get() = success != null
}
