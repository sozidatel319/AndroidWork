package com.aseevei.githubuserstest;

import android.app.Application;

import androidx.room.Room;

import com.aseevei.githubuserstest.di.AppComponent;
import com.aseevei.githubuserstest.di.DaggerAppComponent;
import com.aseevei.githubuserstest.user.data.UserRepository;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenter;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenterImpl;
import com.aseevei.githubuserstest.user.details.presentation.UserSingleListPresenter;
import com.aseevei.githubuserstest.user.details.presentation.UserSingleListPresenterImpl;


public class App extends Application {

    private AppComponent component;
    UserListPresenter userListPresenter;
    UserSingleListPresenter userSingleListPresenter;
    AboutUserDatabase aboutUserDatabase; // сделать как другую database через модуль

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .context(this)
                .build();

        aboutUserDatabase = Room.databaseBuilder(this, AboutUserDatabase.class, "aboutuser").build();
    }

    public UserSingleListPresenter getUserSingleListPresenter(String username) {
        if (userSingleListPresenter == null) {
            userSingleListPresenter = new UserSingleListPresenterImpl(component.getUserRepository(), username); // Проверить всё ли правильно тут
        }
        return userSingleListPresenter;
    }

    public UserListPresenter getUserListPresenter() {
        if (userListPresenter == null) {
            userListPresenter = new UserListPresenterImpl(component.getUserRepository());
        }
        return userListPresenter;
    }

    public void clearUserListPresenter() {
        userListPresenter = null;
    }

}
