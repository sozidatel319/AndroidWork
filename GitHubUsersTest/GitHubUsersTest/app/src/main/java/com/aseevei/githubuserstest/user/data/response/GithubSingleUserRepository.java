package com.aseevei.githubuserstest.user.data.response;

import com.aseevei.githubuserstest.user.api.GitHubSingleUserService;
import com.aseevei.githubuserstest.user.data.SimpleUser;
import com.aseevei.githubuserstest.user.data.User;
import com.aseevei.githubuserstest.user.database.UserDao;

import io.reactivex.Single;

public class GithubSingleUserRepository implements UserSingleRepository {
    private final GitHubSingleUserService service;
    private final UserDao userDao;

    public GithubSingleUserRepository(GitHubSingleUserService service, UserDao userDao) {
        this.service = service;
        this.userDao = userDao;
    }

    @Override
    public Single<SimpleUser> getUser(String username) {
        return service.getUser(username)
                .map(userResponse -> new SimpleUser(
                        userResponse.getId(),
                        userResponse.getName(),
                        userResponse.getAvatarUrl(),
                        userResponse.getWebLink(),
                        userResponse.getLocation(),
                        userResponse.getEmail(),
                        userResponse.getBlog()
                ))
                .doAfterSuccess(user -> {
                    userDao.deleteAll();
                    userDao.insert(user);
                })
                .onErrorResumeNext(error -> {
                    return userDao.getAll();
                });
    }
}
