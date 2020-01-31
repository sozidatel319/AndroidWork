package com.aseevei.githubuserstest.user.api;

import com.aseevei.githubuserstest.user.data.response.UserResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubSingleUserService{

    @GET("users/{username}")
    Single<UserResponse> getUser(@Path("username") String username);
}
