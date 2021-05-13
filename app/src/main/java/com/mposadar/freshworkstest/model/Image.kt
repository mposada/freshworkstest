package com.mposadar.freshworkstest.model

import com.google.gson.annotations.SerializedName

data class Image(
    val original: ImageObject,
    val downsized: ImageObject,
    @SerializedName("downsized_large")
    val downsizedLarge: ImageObject,
    @SerializedName("downsized_medium")
    val downsizedMedium: ImageObject,
    @SerializedName("downsized_small")
    val downsizedSmall: ImageObject,
    @SerializedName("downsized_still")
    val downsizedStill: ImageObject,
    @SerializedName("fixed_height")
    val fixedHeight: ImageObject,
    @SerializedName("fixed_height_downsampled")
    val fixedHeightDownsampled: ImageObject,
    @SerializedName("fixed_height_small")
    val fixedHeightSmall: ImageObject,
    @SerializedName("fixed_height_small_still")
    val fixedHeightSmallStill: ImageObject,
    @SerializedName("fixed_height_still")
    val fixedHeightStill: ImageObject,
    @SerializedName("fixed_width")
    val fixedWidth: ImageObject,
    // ...
)
