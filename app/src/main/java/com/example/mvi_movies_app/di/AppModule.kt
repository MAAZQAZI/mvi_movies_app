package com.example.mvi_movies_app.di

import com.example.mvi_movies_app.data.repository.MovieRepository
import com.example.mvi_movies_app.ui.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module


@OptIn(KoinReflectAPI::class)
val AppModule = module {

    single { MovieRepository() }
    viewModel<MovieViewModel>()

}