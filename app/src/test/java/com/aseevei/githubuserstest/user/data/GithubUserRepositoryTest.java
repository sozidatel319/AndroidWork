package com.aseevei.githubuserstest.user.data;

import com.aseevei.githubuserstest.user.api.GitHubService;
import com.aseevei.githubuserstest.user.data.response.UserResponse;
import com.aseevei.githubuserstest.user.database.AboutUserDao;
import com.aseevei.githubuserstest.user.database.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.reactivex.Single;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class GithubUserRepositoryTest {

    @Mock
    GitHubService service;
    @Mock
    UserDao dao;
    @Mock
    AboutUserDao aboutUserDao;

    private GithubUserRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(service.getUsers()).thenReturn(Single.just(getUsersUiModels()));
        when(dao.getAll()).thenReturn(Single.just(getUsers()));
        repository = new GithubUserRepository(service, dao,aboutUserDao);
    }

    @Test
    public void getUsers_dataLoaded_saveToDatabase_returnUsers() {
        repository.getUsers().test().assertValue(getUsers());

        InOrder inOrder = Mockito.inOrder(dao);
        inOrder.verify(dao).deleteAll();
        inOrder.verify(dao).insert(getUsers());
    }

    @Test
    public void getUsers_loadingError_doNotSaveToDatabase_returnUsersFromCache() {
        when(service.getUsers()).thenReturn(Single.error(new RuntimeException()));
        repository.getUsers().test().assertValue(getUsers());

        verify(dao).getAll();
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void getUsers_loadingError_noCache_returnError() {
        when(service.getUsers()).thenReturn(Single.error(new RuntimeException()));
        when(dao.getAll()).thenReturn(Single.just(new ArrayList<>()));

        repository.getUsers().test().assertError(Exception.class);

        verify(dao).getAll();
        verifyNoMoreInteractions(dao);
    }

    private List<User> getUsers() {
        return Arrays.asList(
                new User(1, "name", "avatar", "url"),
                new User(2, "name2", "avatar", "url")
        );
    }

    private List<UserResponse> getUsersUiModels() {
        return Arrays.asList(
                new UserResponse(1, "name", "avatar", "url"),
                new UserResponse(2, "name2", "avatar", "url")
        );
    }
}
