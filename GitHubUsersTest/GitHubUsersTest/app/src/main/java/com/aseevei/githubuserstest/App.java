package com.aseevei.githubuserstest;

import android.app.Application;

import androidx.room.Room;

import com.aseevei.githubuserstest.user.api.GitHubService;
import com.aseevei.githubuserstest.user.api.GitHubSingleUserService;
import com.aseevei.githubuserstest.user.data.GithubUserRepository;
import com.aseevei.githubuserstest.user.data.response.GithubSingleUserRepository;
import com.aseevei.githubuserstest.user.database.ApplicationDatabase;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenter;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenterImpl;
import com.aseevei.githubuserstest.user.list.presentation.singlepresentation.UserSingleListPresenter;
import com.aseevei.githubuserstest.user.list.presentation.singlepresentation.UserSingleListPresenterImpl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    GithubUserRepository userRepository;
    GithubSingleUserRepository singleUserRepository;
    UserListPresenter userListPresenter;
    UserSingleListPresenter userSingleListPresenter;
    GitHubService gitHubService;
    GitHubSingleUserService gitHubSingleUserService;
    String username;
    ApplicationDatabase database;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCreate() {
        super.onCreate();
            Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
            gitHubService = retrofit.create(GitHubService.class);
            gitHubSingleUserService = retrofit.create(GitHubSingleUserService.class);
            database = Room.databaseBuilder(this,ApplicationDatabase.class,"database").build();
    }

    public GitHubService getGitHubService() {
        return gitHubService;
    }

    public UserSingleListPresenter getUserSingleListPresenter(String username){
        if (userSingleListPresenter == null){
            userSingleListPresenter = new UserSingleListPresenterImpl(getSingleUserRepository(),username);
        }
        return userSingleListPresenter;
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

    private GithubSingleUserRepository getSingleUserRepository(){
        if (singleUserRepository == null){
            singleUserRepository = new GithubSingleUserRepository(gitHubSingleUserService,database.userDao());
        }
        return singleUserRepository;
    }

    private GithubUserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new GithubUserRepository(gitHubService);
        }
        return userRepository;
    }
}
