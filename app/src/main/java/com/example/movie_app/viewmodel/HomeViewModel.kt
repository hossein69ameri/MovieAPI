package com.example.movie_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.model.home.ResponseGenreLIst
import com.example.movie_app.data.model.home.ResponseMoviesLIst
import com.example.movie_app.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMoviesLIst>()
    val genresList = MutableLiveData<ResponseGenreLIst>()
    val lastMoviesList = MutableLiveData<ResponseMoviesLIst>()
    val loading = MutableLiveData<Boolean>()

    fun loadTopMoviesList(id: Int) = viewModelScope.launch {
        val response = repository.topMoviesList(id)
        if (response.isSuccessful) {
            topMoviesList.postValue(response.body())
        }
    }

    fun loadGenresList() = viewModelScope.launch {
        val response = repository.genresList()
        if (response.isSuccessful) {
            genresList.postValue(response.body())
        }
    }

    fun loadLastMoviesList() = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.lastMoviesList()
        if (response.isSuccessful) {
            lastMoviesList.postValue(response.body())
        }
        loading.postValue(false)
    }
}