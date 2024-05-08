package com.bignerdranch.android.wewatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, FilmFragment.newInstance())
                .commit()
        }
    }
}