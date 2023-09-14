package com.example.fetchexercise

//Acts as a repository for handling data related to posts
class PostRepository (private val apiService: ApiService) {

    // Function that fetches a list of posts
    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}
