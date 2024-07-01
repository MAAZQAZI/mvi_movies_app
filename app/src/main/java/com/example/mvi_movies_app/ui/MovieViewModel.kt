package com.example.mvi_movies_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_movies_app.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) :ViewModel() {

    private val _viewState = MutableStateFlow(MovieViewState())
    val viewState = _viewState

fun processIntent(intent: MovieIntent) {
    viewModelScope.launch {
        when (intent) {
            is MovieIntent.LoadMovies -> loadMovies()
            is MovieIntent.SearchMovies -> searchMovies(intent.query)
            is MovieIntent.DeleteMovie -> {
                movieRepository.deleteMovie(intent.movie)
                loadMovies()
            }
        }
    }
}

    private suspend fun loadMovies() {
        _viewState.value = MovieViewState(isLoading = true)
        try {
            val movies = movieRepository.getMovies()
            _viewState.value = MovieViewState(movies = movies)
        } catch (e: Exception) {
            _viewState.value = MovieViewState(error = e.message)
        }
    }

    private suspend fun searchMovies(query: String) {
        _viewState.value = MovieViewState(isLoading = true)
        try {
            val movies = movieRepository.getMoviesByTitle(query)
            _viewState.value = MovieViewState(movies = movies)
        } catch (e: Exception) {
            _viewState.value = MovieViewState(error = e.message)
        }
    }


}
