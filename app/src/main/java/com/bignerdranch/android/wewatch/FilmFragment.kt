package com.bignerdranch.android.wewatch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.wewatch.api.FilmApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FilmFragment"

class FilmFragment : Fragment() {
    private lateinit var filmViewModel: FilmViewModel
    private lateinit var filmRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmViewModel = ViewModelProviders.of(this).get(FilmViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film, container, false)
        filmRecyclerView = view.findViewById(R.id.film_recycler_view)
        filmRecyclerView.layoutManager = GridLayoutManager(context, 1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmViewModel.filmItemLiveData.observe(
            viewLifecycleOwner,
            Observer { filmItems ->
                filmRecyclerView.adapter = FilmAdapter(filmItems)
            }
        )
    }

    private class FilmHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView){
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class FilmAdapter(private val filmItems: List<FilmItem>) : RecyclerView.Adapter<FilmHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
            val textView = TextView(parent.context)
            return FilmHolder(textView)
        }

        override fun getItemCount(): Int = filmItems.size

        override fun onBindViewHolder(holder: FilmHolder, position: Int) {
            val filmItem = filmItems[position]
            holder.bindTitle(filmItem.title)
        }
    }


    companion object{
        fun newInstance() = FilmFragment()
    }
}