package com.mzu.coroutinemvvm.repository

import com.mzu.coroutinemvvm.api.RetrofitClient
import com.mzu.coroutinemvvm.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepository {
    fun getPosts(): Flow<List<Post>> = flow {
        val posts = RetrofitClient.apiService.getPosts()
        emit(posts)
    }
}
