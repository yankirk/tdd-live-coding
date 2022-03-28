package com.picpay.tdd.infra;

import com.picpay.tdd.domain.group.GroupRepository;
import com.picpay.tdd.domain.group.Group;
import com.picpay.tdd.domain.group.GroupUser;
import com.picpay.tdd.domain.group.UserPermission;

import java.util.List;
import java.util.Optional;

public class GroupRepositoryMock implements GroupRepository {

    private String eventId;

    @Override
    public Optional<Group> findByEventId(final String eventId) {
        this.eventId = eventId;

        var user = new GroupUser("any_user_id", UserPermission.ADMIN);
        var group = new Group(List.of(user));

        return Optional.of(group);
    }

    public String getEventId() {
        return eventId;
    }
}
