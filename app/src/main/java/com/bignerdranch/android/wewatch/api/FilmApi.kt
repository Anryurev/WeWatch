package com.bignerdranch.android.wewatch.api

import retrofit2.Call
import retrofit2.http.GET

interface FilmApi{
    @GET("/")
    fun fetchContents(): Call<String>
}