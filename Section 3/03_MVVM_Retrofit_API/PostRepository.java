package com.mzu.mvvmretrofit.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mzu.mvvmretrofit.api.ApiService;
import com.mzu.mvvmretrofit.api.RetrofitClient;
import com.mzu.mvvmretrofit.model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private ApiService apiService;
    private MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();

    public PostRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public LiveData<List<Post>> getPosts() {
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    postsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch posts: " + t.getMessage());
            }
        });
        return postsLiveData;
    }
}
