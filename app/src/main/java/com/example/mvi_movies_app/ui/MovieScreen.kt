package com.example.mvi_movies_app.ui

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mvi_movies_app.data.domain.Movie


import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieScreen(mainViewModel: MovieViewModel= koinViewModel()) {
    val state by mainViewModel.viewState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(mainViewModel) {
        // Trigger the fetchMovies() when the composable is first launched.
        mainViewModel.processIntent(MovieIntent.LoadMovies)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                mainViewModel.processIntent(MovieIntent.SearchMovies(it))
            },
            label = { Text("Search Movies") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        when {
            state.isLoading -> {
                // Display a loading indicator
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }

            state.error != null -> {
                // Display an error message
                Text(text = "Error: ${state.error}", color = Color.Red)
            }

            else -> {
                // Display the list of movies
                MoviesList(movies = state.movies ,onDeleteMovie = {
                    mainViewModel.processIntent(MovieIntent.DeleteMovie(it))
                })
            }
        }
    }

}

@Composable
fun MoviesList(movies: List<Movie> ,onDeleteMovie: (Movie) -> Unit) {
    //create search field

    LazyColumn {
        items(movies) { movie ->
            // Display a movie item
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(
                        4.dp, RoundedCornerShape(8.dp)
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                onDeleteMovie(movie)
                               //add toast here

                            }
                        )
                    }
            ) {
                Text(text = "Movie: ${movie.title}",
                    modifier = Modifier.padding(4.dp))
                Text(text = "Date: ${movie.year}",
                    modifier = Modifier.padding(4.dp))
            }
        }
    }
}