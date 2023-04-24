package com.example.themovieapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.BuildConfig
import com.example.themovieapp.R
import com.example.themovieapp.data.PATH_IMAGES
import com.example.themovieapp.data.model.now.MovieRes
import com.example.themovieapp.domain.model.Movie
import com.squareup.picasso.Picasso

class NowMoviesAdapter(private val context: Context, private val movieItems: List<Movie>):
    RecyclerView.Adapter<NowMoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageMovie : ImageView = itemView.findViewById(R.id.iv_pic_movie)
        //private val tituloMovie: TextView = itemView.findViewById(R.id.tv_titulo_movie)

        fun setMovieImage(movieRes: Movie){
            Picasso.get()
                .load(BuildConfig.BASE_URL_IMAGES.plus(PATH_IMAGES).plus(movieRes.posterPath))
                .into(imageMovie)

            //tituloMovie.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setMovieImage(movieItems[position])
    }
}