package com.example.movie_app.data.model.home


import com.google.gson.annotations.SerializedName

class ResponseGenreLIst : ArrayList<ResponseGenreLIst.ResponseGenreLIstItem>(){
    data class ResponseGenreLIstItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Crime
    )
}