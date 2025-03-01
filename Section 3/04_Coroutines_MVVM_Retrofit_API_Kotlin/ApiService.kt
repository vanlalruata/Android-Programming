package com.mzu.coroutinemvvm.api

import com.mzu.coroutinemvvm.model.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
