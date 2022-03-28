package com.picpay.tdd.domain.group;

public class GroupUser {

    private String userId;
    private UserPermission permission;

    public GroupUser(final String userId, final UserPermission permission) {
        this.userId = userId;
        this.permission = permission;
    }

    public String getUserId() {
        return userId;
    }

    public UserPermission getPermission() {
        return permission;
    }

    public boolean isAdmin() {
        return permission != UserPermission.BASIC;
    }
}
