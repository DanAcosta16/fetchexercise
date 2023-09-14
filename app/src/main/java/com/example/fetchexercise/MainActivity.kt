package com.example.fetchexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp() //Set content to MyApp composable function
        }
    }
}





//Composable function that displays list of posts in a LazyColumn
@Composable
fun PostList(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(posts) { post ->
            // For each post in the list, create a Card
            Card(
                colors = CardDefaults.cardColors(
                    colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Display post information within the Card
                    Text(text = "ID: ${post.id}", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "List ID: ${post.listId}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Name: ${post.name}")
                }
            }
        }
    }
}


// Composable function that represents the UI for the screen
@Composable
fun PostScreen(viewModel: PostViewModel) {
    val scope = rememberCoroutineScope()

    //launch coroutine
    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.getPosts()
        }
    }

    // Collect posts from viewModel
    val posts by viewModel.posts.collectAsState()

    PostList(posts)
}

// Create instances
@Composable
fun MyApp() {
    val postRepository = PostRepository(apiService)
    val getPostsUseCase = GetPostsUseCase(postRepository)
    val viewModel = PostViewModel(getPostsUseCase)
    MaterialTheme {
        PostScreen(viewModel = viewModel)
    }
}