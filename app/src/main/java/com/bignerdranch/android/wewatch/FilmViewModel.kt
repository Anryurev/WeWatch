package com.bignerdranch.android.wewatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FilmViewModel : ViewModel() {
    val filmItemLiveData: LiveData<List<FilmItem>>
    init{
        filmItemLiveData = FilmFetchr().fetchFilms()
    }
}