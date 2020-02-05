package com.aseevei.githubuserstest.user.data;

import java.util.List;

import io.reactivex.Single;

public interface UserRepository {

    Single<List<User>> getUsers();

    Single<SimpleUser> getUser(String username);

}