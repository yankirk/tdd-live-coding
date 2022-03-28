package com.picpay.tdd.infra;

import com.picpay.tdd.domain.event.EventRepository;

public class EventRepositoryMock implements EventRepository {

    private String id;

    @Override
    public void delete(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
