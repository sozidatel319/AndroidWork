package com.aseevei.githubuserstest.user.list.view;

import com.aseevei.githubuserstest.user.list.presentation.UserUIModel;

import java.util.List;

public interface UserListView {

    void showUserList(List<UserUIModel> userList);

    void showErrorMessage(String errorMessage);

    void setProgressVisibility(boolean visible);

    void setRetryButtonVisibility(boolean visible);

}