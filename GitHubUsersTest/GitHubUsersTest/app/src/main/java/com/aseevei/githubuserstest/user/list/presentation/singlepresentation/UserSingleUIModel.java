package com.aseevei.githubuserstest.user.list.presentation.singlepresentation;

import java.util.Objects;

public class UserSingleUIModel {
    private String name;
    private String avatar;
    private String location;
    private String email;
    private String blog;

    public UserSingleUIModel(String name, String avatar, String location, String email, String blog) {
        this.name = name;
        this.avatar = avatar;
        this.location = location;
        this.email = email;
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getBlog() {
        return blog;
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
        UserSingleUIModel that = (UserSingleUIModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avatar);
    }

}
