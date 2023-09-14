package com.example.fetchexercise

import retrofit2.http.GET

// Define an interface for making network requests using Retrofit
interface ApiService {

    //HTTP method and relative URL endpoint
    @GET("hiring.json")

    //async function that retrieves a list of Posts from URL
    suspend fun getPosts(): List<Post>
}