package com.aseevei.githubuserstest.user.list.presentation.singlepresentation;

import com.aseevei.githubuserstest.user.list.view.singleview.UserSingleView;

public interface UserSingleListPresenter {
    void attachView(UserSingleView view);

    void detachView();

    void onFinishing();

    void onRetryButtonClicked();
}
