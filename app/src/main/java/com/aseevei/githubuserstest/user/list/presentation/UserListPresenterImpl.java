package com.aseevei.githubuserstest.user.list.presentation;

import com.aseevei.githubuserstest.user.data.UserRepository;
import com.aseevei.githubuserstest.user.list.view.UserListView;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserListPresenterImpl implements UserListPresenter {

    private UserRepository userRepository;
    private UserListView view;
    private Disposable disposable;
    private List<UserUIModel> userList;

    public UserListPresenterImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void attachView(UserListView view) {
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

    @Override
    public void onClickUser(UserUIModel userUIModel) {
        view.openUpperScreen(userUIModel.getName());
    }

    private void loadUsers() {
        if (isLoading()) {
            throw new RuntimeException("User loading is already in progress");
        }
        disposable = userRepository.getUsers()
                // mapping
                .flatMap(users -> Observable.fromIterable(users)
                        .map(user -> new UserUIModel(user.getName(), user.getAvatarUrl()))
                        .toList())
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