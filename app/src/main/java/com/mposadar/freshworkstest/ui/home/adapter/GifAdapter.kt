package com.mposadar.freshworkstest.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mposadar.freshworkstest.R
import com.mposadar.freshworkstest.databinding.ItemGifBinding
import com.mposadar.freshworkstest.model.Gif
import com.mposadar.freshworkstest.utils.inflater

class GifAdapter(private val addToFavorites: (Gif) -> Unit) :
    PagedListAdapter<Gif, GifViewHolder>(Gif.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = ItemGifBinding.inflate(parent.inflater)
        return GifViewHolder(binding, addToFavorites, ::getItem)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class GifViewHolder(private val binding: ItemGifBinding,
                    private val addToFavorites: (Gif) -> Unit,
                    private val getItem: (Int) -> Gif?) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnFavorite.setOnClickListener {
            getItem(adapterPosition)?.let { gif ->
                addToFavorites(gif)
            }
        }
    }

    fun bind(item: Gif) {
        binding.btnFavorite.isVisible = false
        binding.loadingIndicator.isVisible = true
        binding.ivGif.isInvisible = true
        with(item.images.downsized) {
            Glide.with(binding.root.context).load(url)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        binding.loadingIndicator.isVisible = false
                        binding.ivGif.isInvisible = false
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        binding.ivGif.isInvisible = false
                        binding.loadingIndicator.isVisible = false
                        binding.btnFavorite.isVisible = true
                        return false
                    }
                })
                .placeholder(R.drawable.placeholder)
                .into(binding.ivGif)
        }
    }
}