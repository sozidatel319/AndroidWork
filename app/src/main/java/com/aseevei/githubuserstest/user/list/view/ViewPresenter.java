package com.aseevei.githubuserstest.user.list.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ViewPresenter implements ChangeView{
    public static ChangeView s;
    private FragmentManager fragmentManager;
    private int containerId;
    public ViewPresenter(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
        s = this;
    }

    @Override
    public void setFragment(int containerViewId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
        this.containerId = containerViewId;
    }

    @Override
    public void changeFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerId,fragment).commit();
    }
}
