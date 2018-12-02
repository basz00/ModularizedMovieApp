package com.baz.movie.movies.popular.adapter

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
import com.baz.movie.util.ImageLoader
import com.baz.movie.util.ImageUrlHelper

internal class PopularAdapter(
        private val callback: (String) -> Unit) : ListAdapter<Movie, PopularAdapter.ViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_popular_movie))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val moviePosterImageView = view.findViewById<ImageView>(R.id.popularMoviePosterImageView)
        private val movieNameTextView = view.findViewById<TextView>(R.id.popularMovieNameTextView)
        private val movieRatingTextView = view.findViewById<TextView>(R.id.popularMovieRatingTextView)

        fun bind(movie: Movie) {
            movie.posterPath?.let {
                val posterPath = ImageUrlHelper().generatePosterImageUrl(it)
                ImageLoader().loadImage(moviePosterImageView, posterPath)
            }
            movieNameTextView.text = movie.title
            movieRatingTextView.text = "${movie.voteAverage}"
            movieNameTextView.setOnClickListener { callback.invoke("${movie.id}") }
            movieRatingTextView.setOnClickListener { callback.invoke("${movie.id}") }
            moviePosterImageView.setOnClickListener { callback.invoke("${movie.id}") }
        }
    }

    private class Diff : DiffUtil.ItemCallback<Movie>() {

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = (oldItem.id == newItem.id)

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = (oldItem == newItem)
    }
}