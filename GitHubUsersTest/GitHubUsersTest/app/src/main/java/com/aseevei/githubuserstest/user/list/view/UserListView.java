package com.aseevei.githubuserstest.user.list.view;

import java.util.List;

public interface UserListView {

    void showUserList(List<?> users);

    void showErrorMessage(String errorMessage);

    void setProgressVisibility(boolean visible);

    void setRetryButtonVisibility(boolean visible);

}