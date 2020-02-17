package com.aseevei.githubuserstest.user.list.presentation;

import com.aseevei.githubuserstest.common.RxImmediateSchedulerRule;
import com.aseevei.githubuserstest.user.data.SimpleUser;
import com.aseevei.githubuserstest.user.data.UserRepository;
import com.aseevei.githubuserstest.user.details.presentation.UserSingleListPresenterImpl;
import com.aseevei.githubuserstest.user.details.presentation.UserSingleUIModel;
import com.aseevei.githubuserstest.user.details.view.UserSingleView;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import io.reactivex.subjects.PublishSubject;

@RunWith(RobolectricTestRunner.class)
public class UserSingleListPresenterImplTest {

    @Rule
    public RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();

    @Mock
    UserRepository repository;
    @Mock
    UserSingleView view;
    private PublishSubject<SimpleUser> userObservable = PublishSubject.create();
    private UserSingleListPresenterImpl presenter;
    private String name;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(repository.getUser(name)).thenReturn(userObservable.firstOrError());
        presenter = new UserSingleListPresenterImpl(repository, name);
    }

    @Test
    public void attachView_firstCall_startLoadingData() {
        presenter.attachView(view);
        verify(repository).getUser(name);
        verify(view).setProgressVisibility(true);
    }

    @Test
    public void attachView_firstCall_dataLoaded_showList() {
        attachView_firstCall_startLoadingData();
        userObservable.onNext(getUser());
        verify(view).setProgressVisibility(false);
        verify(view).showUserList(getSingleUIModel());
    }

    @Test
    public void attachView_dataExists_showList() {
        attachView_firstCall_dataLoaded_showList();
        presenter.attachView(view);
        verifyNoMoreInteractions(repository);
        verify(view, times(1)).setProgressVisibility(false);
        verify(view, times(2)).showUserList(getSingleUIModel());
    }

    @Test
    public void attachView_firstCall_dataLoadingError_showError() {
        attachView_firstCall_startLoadingData();
        userObservable.onError(new RuntimeException("Error message"));
        verify(view).setProgressVisibility(false);
        verify(view).setRetryButtonVisibility(true);
        verify(view).showErrorMessage("Error message");
    }

    @Test
    public void attachView_dataIsLoading_doNothing() {
        attachView_firstCall_startLoadingData();
        presenter.attachView(view);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void onFinishing_dataIsLoading_stopLoading() {
        attachView_firstCall_startLoadingData();
        presenter.onFinishing();
        userObservable.onNext(getUser());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onFinishing_viewIsDetached_dataLoaded_noInteractions() {
        attachView_firstCall_startLoadingData();
        presenter.detachView();
        userObservable.onNext(getUser());
        verifyNoMoreInteractions(view);
    }

    @Test(expected = RuntimeException.class)
    public void onRetryButtonClicked_dataIsLoading_throwError() {
        attachView_firstCall_startLoadingData();
        presenter.onRetryButtonClicked();
    }


    private SimpleUser getUser(){
        return new SimpleUser(1,"Georg","url","http","Canada","@","blog");
    }

    private UserSingleUIModel getSingleUIModel(){
        return new UserSingleUIModel("Georg","url","Canada","@","blog");
    }
}
