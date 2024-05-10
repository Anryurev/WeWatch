package com.bignerdranch.android.wewatch

import com.google.gson.annotations.SerializedName

data class FilmItem (
    var title: String = "",
    var genre: String = "",
    var yearRelease: String = "",
    var id: String = "",
    @SerializedName("url_s") var url: String = ""
)
