package com.example.mvi_movies_app

import android.app.Application
import com.example.mvi_movies_app.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(AppModule)
        }
    }
}