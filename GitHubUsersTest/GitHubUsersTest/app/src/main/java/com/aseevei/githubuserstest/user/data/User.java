package com.aseevei.githubuserstest.user.data;

import java.util.Objects;

public final class User {

  private long id;
  private String name;
  private String avatarUrl;
  private String webLink;

  public User(long id, String name, String avatarUrl, String webLink) {
    this.id = id;
    this.name = name;
    this.avatarUrl = avatarUrl;
    this.webLink = webLink;
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