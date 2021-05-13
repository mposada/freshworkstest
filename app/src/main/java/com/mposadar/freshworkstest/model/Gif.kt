package com.mposadar.freshworkstest.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Gif(val type: String,
               val id: String,
               val slug: String,
               val url: String,
               @SerializedName("bitly_url")
               val bitlyUrl: String,
               @SerializedName("embed_url")
               val embedUrl: String,
               val username: String,
               val source: String,
               val rating: String,
               @SerializedName("content_url")
               val contentUrl: String,
               val title: String,
               val images: Image) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Gif> = object : DiffUtil.ItemCallback<Gif>() {
            override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
                return oldItem == newItem
            }
        }
    }
}
