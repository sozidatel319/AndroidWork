package com.aseevei.githubuserstest;

import android.app.Application;

import com.aseevei.githubuserstest.user.data.GithubUserRepository;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenter;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenterImpl;

public class App extends Application {

    GithubUserRepository userRepository;
    UserListPresenter userListPresenter;

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
            userRepository = new GithubUserRepository();
        }
        return userRepository;
    }
}
