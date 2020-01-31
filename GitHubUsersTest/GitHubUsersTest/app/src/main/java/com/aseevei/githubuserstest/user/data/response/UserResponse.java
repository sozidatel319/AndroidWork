package com.aseevei.githubuserstest.user.data.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("login")
    private String name;
    @SerializedName("id")
    private long id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("url")
    private String webLink;
    @SerializedName("location")
    private String location;
    @SerializedName("email")
    private String email;
    @SerializedName("blog")
    private String blog;

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

    public long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getWebLink() {
        return webLink;
    }
}
