package com.example.movie_app.data.repository

import com.example.movie_app.data.remote.ApiServises
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiServises) {

    suspend fun topMoviesList(id: Int) = api.moviesTopList(id)
    suspend fun genresList() = api.genresList()
    suspend fun lastMoviesList() = api.moviesLastList()

}