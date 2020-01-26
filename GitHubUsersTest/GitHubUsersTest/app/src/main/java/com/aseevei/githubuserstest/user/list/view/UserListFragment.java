package com.aseevei.githubuserstest.user.list.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aseevei.githubuserstest.App;
import com.aseevei.githubuserstest.R;
import com.aseevei.githubuserstest.user.list.presentation.UserListPresenter;
import com.aseevei.githubuserstest.user.list.presentation.UserUIModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserListFragment extends Fragment implements UserListView {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.retry_button)
    Button retryButton;

    private Unbinder unbinder;
    private UserAdapter adapter;
    private UserListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = ((App) getActivity().getApplication()).getUserListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        retryButton.setOnClickListener(button -> presenter.onRetryButtonClicked());
        adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        presenter.attachView(this);

        return view;
    }

    @Override
    public void showUserList(List<UserUIModel> userList) {
        adapter.addUsers(userList);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressVisibility(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setRetryButtonVisibility(boolean visible) {
        retryButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (getActivity().isFinishing()) {
            presenter.onFinishing();
            ((App) getActivity().getApplication()).clearUserListPresenter();
        }
        super.onDestroy();
    }
}
