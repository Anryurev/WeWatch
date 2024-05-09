package com.bignerdranch.android.wewatch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.wewatch.api.FilmApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

private const val TAG = "FilmFetchr"

class FilmFetchr{
    private val filmApi: FilmApi
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        filmApi = retrofit.create(FilmApi::class.java)
    }
    fun fetchContents(): LiveData<String>{
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val filmRequest: Call<String> = filmApi.fetchContents()
        filmRequest.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body()
            }
        })
        return responseLiveData
    }
}