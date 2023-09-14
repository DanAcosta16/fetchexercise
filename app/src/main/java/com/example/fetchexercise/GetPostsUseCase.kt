package com.example.fetchexercise


// This represents a use case responsible for retrieving a list of Posts
class GetPostsUseCase (private val postRepository: PostRepository) {
    //called when instance is invoked
    suspend operator fun invoke(): List<Post> {
        return postRepository.getPosts()
    }
}