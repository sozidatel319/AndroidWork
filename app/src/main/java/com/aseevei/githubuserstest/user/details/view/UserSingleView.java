package com.aseevei.githubuserstest.user.details.view;

import com.aseevei.githubuserstest.user.details.presentation.UserSingleUIModel;

public interface UserSingleView {

    void showUserList(UserSingleUIModel user);

    void showErrorMessage(String errorMessage);

    void setProgressVisibility(boolean visible);

    void setRetryButtonVisibility(boolean visible);
}
