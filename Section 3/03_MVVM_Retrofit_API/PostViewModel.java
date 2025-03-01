package com.mzu.mvvmretrofit.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.mzu.mvvmretrofit.model.Post;
import com.mzu.mvvmretrofit.repository.PostRepository;
import java.util.List;

public class PostViewModel extends ViewModel {
    private PostRepository repository;
    private LiveData<List<Post>> posts;

    public PostViewModel() {
        repository = new PostRepository();
        posts = repository.getPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }
}
