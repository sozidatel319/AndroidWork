package com.aseevei.githubuserstest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aseevei.githubuserstest.user.list.view.UserListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new UserListFragment())
                    .commit();
        }
    }

}