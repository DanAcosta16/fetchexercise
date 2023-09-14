package com.example.fetchexercise

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Serves as the viewModel for managing data related to posts
class PostViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    // Current state of post data
    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts: StateFlow<List<Post>> = _posts

    // Fetch posts and apply filters
    suspend fun getPosts() {
        //grab all posts from url
        val allPosts = getPostsUseCase()
        //filter out null or blank and sort
        val filteredPosts = allPosts.filter { it.name?.isNotBlank() == true }
            .sortedWith(compareBy({ it.listId }, {it.name }))
            .groupBy { it.listId }

        _posts.value = filteredPosts.values.flatten()
    }
}