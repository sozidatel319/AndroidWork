package com.aseevei.githubuserstest.user.details.presentation;

import com.aseevei.githubuserstest.user.data.UserRepository;
import com.aseevei.githubuserstest.user.details.view.UserSingleView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserSingleListPresenterImpl implements UserSingleListPresenter {

    private UserRepository userRepository;
    private UserSingleView view;
    private Disposable disposable;
    private UserSingleUIModel userList;
    private String username;

    public UserSingleListPresenterImpl(UserRepository userRepository, String username) {
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public void attachView(UserSingleView view) {
        this.view = view;
        if (isLoading()) {
            view.setProgressVisibility(true);
            return;
        }

        if (userList != null) {
            view.showUserList(userList);
        } else {
            loadUsers();
        }
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onFinishing() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onRetryButtonClicked() {
        view.setRetryButtonVisibility(false);
        loadUsers();
    }

    private void loadUsers() {
        if (isLoading()) {
            throw new RuntimeException("User loading is already in progress");
        }
        disposable = userRepository.getUser(username)
                // mapping
                .map(user -> new UserSingleUIModel(user.getName(), user.getAvatarUrl(), user.getLocation(), user.getEmail(), user.getBlog()))
                // multithreading
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .doOnSubscribe(disposable -> view.setProgressVisibility(true))
                .doOnSuccess(list -> userList = list)

                // check if view is not detached
                .filter(users -> view != null)

                // hide progress bar
                .doAfterTerminate(() -> view.setProgressVisibility(false))


                .subscribe(users -> view.showUserList(users), // show users is success
                        error -> {
                            // show error message, show retry button
                            view.showErrorMessage(error.getMessage());
                            view.setRetryButtonVisibility(true);
                        });
    }

    private boolean isLoading() {
        return disposable != null && !disposable.isDisposed();
    }

}