package com.mposadar.freshworkstest.model

import com.google.gson.annotations.SerializedName

data class ImageObject(
    val height: String? = null,
    val width: String? = null,
    val size: String? = null,
    val url: String? = null,
    @SerializedName("mp4_size")
    val mp4Size: String? = null,
    val mp4: String? = null,
    @SerializedName("webp_size")
    val webpSize: String? = null,
    val webp: String? = null,
    val frames: String? = null,
    val hash: String? = null,
)
