package com.aseevei.githubuserstest.user.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class SimpleUser {
    @PrimaryKey
    private long id;
    private String name;
    private String avatarUrl;
    private String webLink;
    private String location;
    private String email;
    private String blog;
    public long getId() {
        return id;
    }

    public SimpleUser(long id, String name, String avatarUrl, String webLink, String location, String email, String blog) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.webLink = webLink;
        this.location = location;
        this.email = email;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getWebLink() {
        return webLink;
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


}
