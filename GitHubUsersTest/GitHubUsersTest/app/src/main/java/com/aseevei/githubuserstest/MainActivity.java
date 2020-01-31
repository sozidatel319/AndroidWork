package com.aseevei.githubuserstest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.aseevei.githubuserstest.user.list.view.UserListFragment;
import com.aseevei.githubuserstest.user.list.view.ViewPresenter;
import com.aseevei.githubuserstest.user.list.view.singleview.AboutUserFragment;

public class MainActivity extends AppCompatActivity {
    ViewPresenter viewPresenter = new ViewPresenter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            viewPresenter.setFragment(R.id.container,new UserListFragment());
            //viewPresenter.setFragment(R.id.container, new AboutUserFragment());
        }
    }

}