package com.bignerdranch.android.wewatch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.wewatch.api.FilmApi
import com.bignerdranch.android.wewatch.api.FilmResponse
import com.bignerdranch.android.wewatch.api.OMDbResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

private const val TAG = "FilmFetchr"

class FilmFetchr{
    private val filmApi: FilmApi
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        filmApi = retrofit.create(FilmApi::class.java)
    }
    fun fetchFilms(): LiveData<List<FilmItem>>{
        val responseLiveData: MutableLiveData<List<FilmItem>> = MutableLiveData()
        val filmRequest: Call<OMDbResponse> = filmApi.fetchFilms()
        filmRequest.enqueue(object : Callback<OMDbResponse>{
            override fun onFailure(call: Call<OMDbResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<OMDbResponse>, response: Response<OMDbResponse>) {
                Log.d(TAG, "Response received")
                val omdbResponse: OMDbResponse? = response.body()
                val filmResponse: FilmResponse? = omdbResponse?.poster
                var filmItems: List<FilmItem> = filmResponse?.filmItems?: mutableListOf()
                filmItems = filmItems.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = filmItems
            }
        })
        return responseLiveData
    }
}