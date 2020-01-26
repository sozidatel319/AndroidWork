package com.aseevei.githubuserstest.user.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class GithubUserRepository implements UserRepository {
    Retrofit retrofit =

    @Override
    public Single<List<User>> getUsers() {
        return Single.fromCallable(this::getTempUsers).delay(8, TimeUnit.SECONDS);
    }

    private List<User> getTempUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1, "Vasya", "", ""));
        users.add(new User(1, "Vasya1", "", ""));
        users.add(new User(1, "Vasya2", "", ""));
        users.add(new User(1, "Vasya3", "", ""));
        users.add(new User(1, "Vasya4", "", ""));
        users.add(new User(1, "Vasya5", "", ""));
        return users;
    }

}