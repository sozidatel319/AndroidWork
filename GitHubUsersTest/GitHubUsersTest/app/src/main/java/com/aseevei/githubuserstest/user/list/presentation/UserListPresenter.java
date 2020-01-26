package com.aseevei.githubuserstest.user.list.presentation;

import com.aseevei.githubuserstest.user.list.view.UserListView;

public interface UserListPresenter {

    void attachView(UserListView view);

    void detachView();

    void onFinishing();

    void onRetryButtonClicked();

}