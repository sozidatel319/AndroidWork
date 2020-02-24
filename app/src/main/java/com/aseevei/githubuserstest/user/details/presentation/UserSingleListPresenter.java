package com.aseevei.githubuserstest.user.details.presentation;

import com.aseevei.githubuserstest.user.details.view.UserSingleView;

public interface UserSingleListPresenter {
    void attachView(UserSingleView view);

    void detachView();

    void onFinishing();

    void onRetryButtonClicked();

    String getName();

}