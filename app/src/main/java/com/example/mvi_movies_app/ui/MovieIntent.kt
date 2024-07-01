package com.example.mvi_movies_app.ui

import com.example.mvi_movies_app.data.domain.Movie

sealed class MovieIntent {
    object LoadMovies: MovieIntent()
    data class SearchMovies(val query: String): MovieIntent()
    data class DeleteMovie(val movie: Movie) : MovieIntent()
}