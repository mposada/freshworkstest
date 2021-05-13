package com.mposadar.freshworkstest.ui.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mposadar.freshworkstest.database.model.GifEntity
import com.mposadar.freshworkstest.databinding.ItemFavoriteBinding
import com.mposadar.freshworkstest.utils.inflater

class FavoritesAdapter(private val deleteFavorite: (GifEntity) -> Unit): RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    var data: List<GifEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(parent.inflater)
        return FavoriteViewHolder(binding, deleteFavorite, ::getItem)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        data?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    private fun getItem(position: Int): GifEntity? = data?.get(position)

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding,
        private val deleteFavorite: (GifEntity) -> Unit,
        private val getItem: (Int) -> GifEntity?) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivDeleteFavorite.setOnClickListener {
                getItem(adapterPosition)?.let { gif ->
                    deleteFavorite(gif)
                }
            }
        }

        fun bind(item: GifEntity) {
            Glide.with(binding.root.context).load(item.url).into(binding.ivFavorite)
        }
    }
}