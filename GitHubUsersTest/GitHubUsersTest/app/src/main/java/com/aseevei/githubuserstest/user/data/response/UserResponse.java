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
