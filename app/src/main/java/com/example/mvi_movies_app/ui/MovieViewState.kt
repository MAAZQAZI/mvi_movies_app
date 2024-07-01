package com.example.mvi_movies_app.ui;

import com.example.mvi_movies_app.data.domain.Movie;

data class MovieViewState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)