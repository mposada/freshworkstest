package com.mposadar.freshworkstest.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mposadar.freshworkstest.FreshWorksApplication
import com.mposadar.freshworkstest.databinding.FragmentHomeBinding
import com.mposadar.freshworkstest.model.Gif
import com.mposadar.freshworkstest.ui.home.adapter.GifAdapter
import com.mposadar.freshworkstest.utils.AppViewModelFactory
import com.mposadar.freshworkstest.utils.debounce
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        AppViewModelFactory(FreshWorksApplication.databaseRepository)
    }
    private val adapter = GifAdapter(::addToFavorites)

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            viewModel.updateSearchInputValue(s.toString())
        }

        override fun afterTextChanged(editable: Editable?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.gifResults.observe(this) {
            adapter.submitList(it)
        }

        viewModel.searchInput.debounce(500L, CoroutineScope(Dispatchers.Main)).observe(this) {
            viewModel.performSearch(it)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvGifs.adapter = this@HomeFragment.adapter
            searchInput.addTextChangedListener(textWatcher)
        }
    }

    private fun addToFavorites(gif: Gif) {
        viewModel.addToFavorites(gif)
        Toast.makeText(context, "Gif added to favorites", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}