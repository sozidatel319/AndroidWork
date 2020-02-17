package com.aseevei.githubuserstest.user.di;

import com.aseevei.githubuserstest.di.PerApplication;
import com.aseevei.githubuserstest.user.api.GitHubService;
import com.aseevei.githubuserstest.user.data.GithubUserRepository;
import com.aseevei.githubuserstest.user.data.UserRepository;
import com.aseevei.githubuserstest.user.database.AboutUserDao;
import com.aseevei.githubuserstest.user.database.UserDao;
import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    @PerApplication
    UserRepository provideUserRepository(GitHubService gitHubService, UserDao userDao, AboutUserDao aboutUserDao) {
        return new GithubUserRepository(gitHubService, userDao, aboutUserDao);
    }
}

