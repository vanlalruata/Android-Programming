package com.mzu.coroutinemvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mzu.coroutinemvvm.adapter.PostAdapter
import com.mzu.coroutinemvvm.viewmodel.PostViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        postViewModel.fetchPosts()

        lifecycleScope.launch {
            postViewModel.posts.collect { postList ->
                adapter.setPosts(postList)
            }
        }
    }
}
