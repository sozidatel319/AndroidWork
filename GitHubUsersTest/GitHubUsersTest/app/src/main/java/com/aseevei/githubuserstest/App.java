package com.aseevei.githubuserstest;

import android.app.Application;

import com.aseevei.githubuserstest.user.api.GitHubService;
import com.aseevei.githubuserstest.user.data.GithubUserRepository;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenter;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenterImpl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    GithubUserRepository userRepository;
    UserListPresenter userListPresenter;
    GitHubService gitHubService;

    @Override
    public void onCreate() {
        super.onCreate();
            Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
            gitHubService = retrofit.create(GitHubService.class);
    }

    public GitHubService getGitHubService() {
        return gitHubService;
    }

    public UserListPresenter getUserListPresenter() {
        if (userListPresenter == null) {
            userListPresenter = new UserListPresenterImpl(getUserRepository());
        }
        return userListPresenter;
    }

    public void clearUserListPresenter() {
        userListPresenter = null;
    }

    private GithubUserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new GithubUserRepository(gitHubService);
        }
        return userRepository;
    }
}
