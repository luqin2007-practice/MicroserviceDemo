package com.example.shopping.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public final class UserMq implements Serializable {

    public static final String USER_UPDATED = "USER_UPDATED";
    public static final String USER_DELETED = "USER_DELETED";

    @Serial
    private static final long serialVersionUID = 0L;

    private final String type;
    private final UserDto user;

    public UserMq(String type, UserDto user) {
        this.type = type;
        this.user = user;
    }

    public static UserMq updateUser(UserDto user) {
        return new UserMq(USER_UPDATED, user);
    }

    public static UserMq deleteUser(Long id) {
        return new UserMq(USER_DELETED, new UserDto(id, "", "", 0));
    }

    public String type() {
        return type;
    }

    public UserDto user() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserMq) obj;
        return Objects.equals(this.type, that.type) &&
                Objects.equals(this.user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, user);
    }

    @Override
    public String toString() {
        return "UserMq[" +
                "type=" + type + ", " +
                "user=" + user + ']';
    }
}
