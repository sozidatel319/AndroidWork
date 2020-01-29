package com.aseevei.githubuserstest.user.api;

import com.aseevei.githubuserstest.user.data.response.UserResponse;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface GitHubService {
   @GET("users")
   Single<List<UserResponse>> getUsers();
}
