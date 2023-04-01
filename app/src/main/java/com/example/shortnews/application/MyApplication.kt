package com.example.shortnews.application

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    companion object{
        lateinit var retrofit: Retrofit
        private set
    }

    override fun onCreate() {
        super.onCreate()
         retrofit= Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }
}