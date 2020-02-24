package com.aseevei.githubuserstest.user.list.presentation;

import java.util.Objects;

public class UserUIModel {

    private String name;
    private String avatar;

    public UserUIModel(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUIModel that = (UserUIModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avatar);
    }

}