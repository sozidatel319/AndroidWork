package com.aseevei.githubuserstest.user.api;

import com.aseevei.githubuserstest.user.data.response.AboutUserResponse;
import com.aseevei.githubuserstest.user.data.response.UserResponse;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users")
    Single<List<UserResponse>> getUsers();

    @GET("users/{username}")
    Single<AboutUserResponse> getUser(@Path("username") String username);
}
