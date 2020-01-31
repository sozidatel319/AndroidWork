package com.aseevei.githubuserstest.user.data.response;

import com.aseevei.githubuserstest.user.data.SimpleUser;
import io.reactivex.Single;

public interface UserSingleRepository {
    Single<SimpleUser> getUser(String username);
}
