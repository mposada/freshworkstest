package com.mposadar.freshworkstest.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mposadar.freshworkstest.FreshWorksApplication
import com.mposadar.freshworkstest.database.model.GifEntity
import com.mposadar.freshworkstest.databinding.FragmentFavoritesBinding
import com.mposadar.freshworkstest.ui.favorites.adapter.FavoritesAdapter
import com.mposadar.freshworkstest.utils.AppViewModelFactory

class FavoritesFragment : Fragment() {

    lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels {
        AppViewModelFactory(FreshWorksApplication.databaseRepository)
    }
    private val adapter = FavoritesAdapter(::deleteFavorite)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.favoriteGifs.observe(this) {
            adapter.data = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvFavoritesGifs) {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = this@FavoritesFragment.adapter
        }
    }

    private fun deleteFavorite(gifEntity: GifEntity) {
        viewModel.deleteFavorite(gifEntity)
        Toast.makeText(context, "Gif removed from favorites", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val SPAN_COUNT = 2

        fun newInstance() = FavoritesFragment()
    }
}