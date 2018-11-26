package com.baz.movie.movies.nowplaying.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baz.movie.R
import com.baz.movie.extensions.inflate
import com.baz.movie.movies.data.Movie
import com.baz.movie.movies.nowplaying.adapter.NowPlayingAdapter.ViewHolder

internal class NowPlayingAdapter : ListAdapter<Movie, ViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.movie_item_grid))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val moviePosterImageView = view.findViewById<ImageView>(R.id.moviePosterImageView)
        private val movieNameTextView = view.findViewById<TextView>(R.id.movieNameTextView)

        fun bind(movie: Movie) {
            movieNameTextView.text = movie.title
        }
    }

    private class Diff : DiffUtil.ItemCallback<Movie>() {

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = (oldItem.id == newItem.id)

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = (oldItem == newItem)
    }
}