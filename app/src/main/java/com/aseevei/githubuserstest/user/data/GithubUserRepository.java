package com.aseevei.githubuserstest.user.data;

import com.aseevei.githubuserstest.user.api.GitHubService;
import com.aseevei.githubuserstest.user.database.AboutUserDao;
import com.aseevei.githubuserstest.user.database.UserDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class GithubUserRepository implements UserRepository {

    private final GitHubService service;
    private UserDao userDao;
    private AboutUserDao aboutUserDao;

    public GithubUserRepository(GitHubService service, UserDao userDao, AboutUserDao aboutUserDao) {
        this.service = service;
        this.userDao = userDao;
        this.aboutUserDao = aboutUserDao;
    }

   /* public GithubUserRepository(GitHubService service, AboutUserDao aboutUserDao) {
        this.service = service;
        this.aboutUserDao = aboutUserDao;
    }*/

    @Override
    public Single<List<User>> getUsers() {
        return service.getUsers()
                .flatMap(userResponseList -> Observable.fromIterable(userResponseList)
                        .map(userResponse -> new User(
                                userResponse.getId(),
                                userResponse.getName(),
                                userResponse.getAvatarUrl(),
                                userResponse.getWebLink()))
                        .toList()
                        .doAfterSuccess(users -> {
                            userDao.deleteAll();
                            userDao.insert(users);
                        })
                        .onErrorResumeNext(error -> userDao.getAll().flatMap(cachedUsers -> cachedUsers.isEmpty()
                                ? Single.error(new Exception("No users found"))
                                : Single.just(cachedUsers)
                        ))
                );
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
                .doAfterSuccess(aboutUserDao::insert)
                .onErrorResumeNext(error -> aboutUserDao.getAll());
    }

}