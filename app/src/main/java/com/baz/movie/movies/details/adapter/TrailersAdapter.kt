package com.baz.movie.movies.details.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baz.movie.R
import com.baz.movie.extensions.inflate
import com.baz.movie.movies.data.Cast
import com.baz.movie.movies.data.YoutubeTrailer
import com.baz.movie.util.ImageLoader
import com.baz.movie.util.ImageUrlHelper
import com.baz.movie.movies.details.adapter.TrailersAdapter.ViewHolder

internal class TrailersAdapter : ListAdapter<YoutubeTrailer, ViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_trailer))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val trailerImageView = view.findViewById<ImageView>(R.id.trailerImageView)

        fun bind(youtubeTrailer: YoutubeTrailer) {
            val castProfilePath = ImageUrlHelper().generateYoutubeThumbnailUrl(youtubeTrailer.key)
            ImageLoader().loadImage(trailerImageView, castProfilePath)
        }
    }

    private class Diff : DiffUtil.ItemCallback<YoutubeTrailer>() {

        override fun areItemsTheSame(oldItem: YoutubeTrailer, newItem: YoutubeTrailer) = (oldItem.id == newItem.id)

        override fun areContentsTheSame(oldItem: YoutubeTrailer, newItem: YoutubeTrailer) = (oldItem == newItem)
    }
}