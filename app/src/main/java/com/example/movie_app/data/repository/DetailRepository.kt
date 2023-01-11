package com.example.movie_app.data.repository


import com.example.movie_app.data.remote.ApiServises
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServises) {
    //Api
    suspend fun detailMovie(id: Int) = api.detailMovie(id)
}