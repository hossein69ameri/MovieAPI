package com.example.movie_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentHomeBinding
import com.example.movie_app.ui.home.adapter.GenresAdapter
import com.example.movie_app.ui.home.adapter.LastMoviesAdapter
import com.example.movie_app.ui.home.adapter.TopMoviesAdapter
import com.example.movie_app.util.initRecycler
import com.example.movie_app.util.showInvisible
import com.example.movie_app.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    //Other
    private val viewModel: HomeViewModel by viewModels()
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Call api
        viewModel.loadTopMoviesList(3)
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
        //InitViews
        binding.apply {
            //Get top movies
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)
                //RecyclerView
                topMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter
                )
                //Indicator
                pagerHelper.attachToRecyclerView(topMoviesRecycler)
                topMoviesIndicator.attachToRecyclerView(topMoviesRecycler, pagerHelper)
            }
            //Get genres
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)
                genresRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter
                )
            }
            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.data)
                //RecyclerView
                lastMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext()),
                    lastMoviesAdapter
                )
            }
            //Click
            lastMoviesAdapter.setOnItemClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id!!.toInt())
                findNavController().navigate(direction)
            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInvisible(true)
                    moviesScrollLay.showInvisible(false)
                } else {
                    moviesLoading.showInvisible(false)
                    moviesScrollLay.showInvisible(true)
                }
            }
        }
    }
}
