package com.example.themovieapp.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remote.di.service.ApiConstants
import com.example.themovieapp.R
import com.example.themovieapp.domain.model.Movie
import com.squareup.picasso.Picasso

class TopMoviesAdapter(private val context: Context, private val listMovies: List<Movie>):
    RecyclerView.Adapter<TopMoviesAdapter.MovieTopViewHolder>() {

    inner class MovieTopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageTopMovie : ImageView = itemView.findViewById(R.id.imageViewDog)
        private val tituloMovie: TextView = itemView.findViewById(R.id.tv_top_title_movie)
        private val releaseDate: TextView = itemView.findViewById(R.id.tv_top_movie_date)
        private val voteAverage: TextView = itemView.findViewById(R.id.tv_vote_top_movie)


        @SuppressLint("SetTextI18n")
        fun setMovieImage(movie: Movie){
            Picasso.get()
                .load(ApiConstants.BASE_URL_IMAGES.plus(ApiConstants.PATH_IMAGES).plus(movie.posterPath))
                .into(imageTopMovie)

            tituloMovie.text = movie.title
            releaseDate.text = movie.releaseDate
            voteAverage.text = movie.voteAverage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTopViewHolder {
        return MovieTopViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_movie, parent, false))
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieTopViewHolder, position: Int) {
        holder.setMovieImage(listMovies[position])
    }
}