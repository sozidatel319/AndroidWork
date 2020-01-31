package com.aseevei.githubuserstest.user.list.view.singleview;

import com.aseevei.githubuserstest.user.list.presentation.singlepresentation.UserSingleUIModel;

public interface UserSingleView {

    void showUserList(UserSingleUIModel user);

    void showErrorMessage(String errorMessage);

    void setProgressVisibility(boolean visible);

    void setRetryButtonVisibility(boolean visible);
}
