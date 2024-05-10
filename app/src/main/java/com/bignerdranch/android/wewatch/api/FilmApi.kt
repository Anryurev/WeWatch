package com.bignerdranch.android.wewatch.api

import retrofit2.Call
import retrofit2.http.GET

interface FilmApi{
    @GET("http://www.omdbapi.com/?i=tt3896198" + "&apikey=b1f8c1d4" + "&format=json" + "&nojsoncallback=1" + "&extras=url_s")
    fun fetchFilms(): Call<OMDbResponse>
}