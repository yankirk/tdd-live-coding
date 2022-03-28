package com.picpay.tdd.domain.group;

import java.util.Optional;

public interface GroupRepository {

    Optional<Group> findByEventId(final String eventId);
}
