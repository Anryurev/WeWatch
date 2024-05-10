package com.bignerdranch.android.wewatch.api

import com.bignerdranch.android.wewatch.FilmItem
import com.google.gson.annotations.SerializedName

class FilmResponse {
    @SerializedName("Poster")
    lateinit var filmItems: List<FilmItem>
}