package com.picpay.tdd.domain.group;

import java.util.List;

public class Group {

    private List<GroupUser> users;

    public Group(final List<GroupUser> users) {
        this.users = users;
    }

    public boolean isAdmin(final String userId) {
        return users.stream().anyMatch(user -> user.getUserId().equals(userId) && user.isAdmin());
    }
}
