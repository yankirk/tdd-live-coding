package com.picpay.tdd.domain.event;

import com.picpay.tdd.domain.group.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EventService {

    private final GroupRepository groupRepository;
    private final EventRepository repository;

    public EventService(final GroupRepository groupRepository, final EventRepository repository) {
        this.groupRepository = groupRepository;
        this.repository = repository;
    }

    public void remove(final String id, final String userId) {
        var groupOptional = this.groupRepository.findByEventId(id);

        if(groupOptional.isEmpty() || !groupOptional.get().isAdmin(userId)) {
            throw new NoSuchElementException();
        }

        repository.delete(id);
    }

}
