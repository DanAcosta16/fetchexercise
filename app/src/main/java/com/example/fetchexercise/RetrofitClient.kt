package com.example.fetchexercise

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit instance for making network requests
val retrofit = Retrofit.Builder()
    .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//Create api interface for defining API endpoints
val apiService = retrofit.create(ApiService::class.java)