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
import com.baz.movie.util.ImageLoader
import com.baz.movie.util.ImageUrlHelper
import com.baz.movie.movies.details.adapter.CastsAdapter.ViewHolder

internal class CastsAdapter: ListAdapter<Cast, ViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cast))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val castImageView = view.findViewById<ImageView>(R.id.castImageView)
        private val castNameTextView = view.findViewById<TextView>(R.id.castNameTextView)

        fun bind(cast: Cast){
            castNameTextView.text = cast.name
            val castProfilePath = ImageUrlHelper().generatePosterImageUrl(cast.profilePath)
            ImageLoader().loadImage(castImageView, castProfilePath)
        }
    }

    private class Diff: DiffUtil.ItemCallback<Cast>(){

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast) = (oldItem.id == newItem.id)

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast) = (oldItem == newItem)
    }
}