package com.aseevei.githubuserstest.user.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
@Entity
public class SimpleUser {
    @PrimaryKey
    private long id;
    private String name;
    private String avatarUrl;
    private String webLink;
    private String location;
    private String email;
    private String blog;

    public SimpleUser(long id, String name, String avatarUrl, String webLink, String location, String email, String blog) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.webLink = webLink;
        this.location = location;
        this.email = email;
        this.blog = blog;
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUser user = (SimpleUser) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(avatarUrl, user.avatarUrl) &&
                Objects.equals(webLink, user.webLink) &&
                Objects.equals(location, user.location) &&
                Objects.equals(email, user.email) &&
                Objects.equals(blog, user.blog);
    }


}
