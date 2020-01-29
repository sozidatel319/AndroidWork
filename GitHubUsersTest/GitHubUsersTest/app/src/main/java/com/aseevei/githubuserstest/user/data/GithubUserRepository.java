package com.aseevei.githubuserstest.user.data;

import com.aseevei.githubuserstest.user.api.GitHubService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class GithubUserRepository implements UserRepository {

    private final GitHubService service;

    public GithubUserRepository(GitHubService service) {
        this.service = service;
    }

    @Override
    public Single<List<User>> getUsers() {
        return service.getUsers()
                .flatMap(users -> Observable.fromIterable(users)
                        .map(userResponse -> new User(
                                userResponse.getId(),
                                userResponse.getName(),
                                userResponse.getAvatarUrl(),
                                userResponse.getWebLink()))
                        .toList()
                );
    }

}