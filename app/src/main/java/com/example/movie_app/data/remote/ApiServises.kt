package com.example.movie_app.data.remote

import com.example.movie_app.data.model.home.ResponseGenreLIst
import com.example.movie_app.data.model.home.ResponseMoviesLIst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServises {

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesLIst>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenreLIst>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesLIst>

}