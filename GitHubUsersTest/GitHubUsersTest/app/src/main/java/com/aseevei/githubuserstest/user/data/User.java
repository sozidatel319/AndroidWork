package com.aseevei.githubuserstest.user.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "users")
public final class User {

  @PrimaryKey
  private long id;
  private String name;
  private String avatarUrl;
  private String webLink;
  private String location;
  private String email;
  private String blog;


  public User(long id, String name, String avatarUrl, String webLink) {
    this.id = id;
    this.name = name;
    this.avatarUrl = avatarUrl;
    this.webLink = webLink;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBlog() {
    return blog;
  }

  public void setBlog(String blog) {
    this.blog = blog;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getWebLink() {
    return webLink;
  }

  public void setWebLink(String webLink) {
    this.webLink = webLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id &&
        Objects.equals(name, user.name) &&
        Objects.equals(avatarUrl, user.avatarUrl) &&
        Objects.equals(webLink, user.webLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, avatarUrl, webLink);
  }

}