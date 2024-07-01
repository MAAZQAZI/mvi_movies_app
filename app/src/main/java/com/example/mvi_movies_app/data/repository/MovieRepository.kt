package com.example.mvi_movies_app.data.repository

import com.example.mvi_movies_app.data.domain.Movie
import kotlinx.coroutines.delay

class MovieRepository {
    val movies = mutableListOf<Movie>(
        Movie(1, "Alita Battle Angel", "2019"),
        Movie(2, "Mortal Engines", "2018"),
        Movie(3, "Avatar The Way of Water", "2022"),
        Movie(4, "Lost in Space", "2018"),
        Movie(1, "Alita Battle Angel", "2019"),
        Movie(2, "Mortal Engines", "2018"),
        Movie(3, "Avatar The Way of Water", "2022"),
        Movie(4, "Lost in Space", "2018"),
        Movie(1, "Alita Battle Angel", "2019"),
        Movie(2, "Mortal Engines", "2018"),
        Movie(3, "Avatar The Way of Water", "2022"),
        Movie(4, "Lost in Space", "2018"),
        Movie(4, "Lost in Space", "2018"),
        Movie(1, "Alita Battle Angel", "2019"),
        Movie(2, "Mortal Engines", "2018"),
        Movie(3, "Avatar The Way of Water", "2022"),
        Movie(4, "Lost in Space", "2018"),
        Movie(4, "Lost in Space", "2018"),
        Movie(1, "Alita Battle Angel", "2019"),
        Movie(2, "Mortal Engines", "2018"),
        Movie(3, "Avatar The Way of Water", "2022"),
        Movie(4, "Lost in Space", "2018")
    )
    suspend fun getMovies(): List<Movie> {
        delay(2000)
        return movies
    }

    suspend fun getMoviesByTitle(title: String): List<Movie> {
        //delay(2000)
        return getMovies().filter { it.title.contains(title, ignoreCase = true) }
    }

    suspend fun deleteMovie(movie: Movie) {
        movies.remove(movie)
    }

}