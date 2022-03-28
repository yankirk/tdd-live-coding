package com.picpay.tdd.domain;

import com.picpay.tdd.domain.event.EventService;
import com.picpay.tdd.domain.group.Group;
import com.picpay.tdd.domain.group.GroupUser;
import com.picpay.tdd.domain.group.UserPermission;
import com.picpay.tdd.infra.EventRepositoryMock;
import com.picpay.tdd.infra.GroupRepositoryMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EventService.class)
@DisplayName("Test Event Domain Service")
class EventServiceTest {
    private static final String EVENT_ID = "any_event_id";
    private static final String USER_ID = "any_user_id";

    @Autowired EventService sut; //System Under Test
    @SpyBean GroupRepositoryMock groupRepository;
    @SpyBean EventRepositoryMock eventRepository;

    @Test
    @DisplayName("When requested event group Then return group")
    void whenRequestedEventGroupThenReturnGroup() {

        sut.remove(EVENT_ID, USER_ID);

        assertThat(groupRepository.getEventId()).isEqualTo(EVENT_ID);
        verify(groupRepository, times(1)).findByEventId(EVENT_ID);
    }

    @Test
    @DisplayName("When event id is invalid Then throw exception")
    void whenEventIdInvalidThenThrowException() {

        when(groupRepository.findByEventId(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sut.remove(EVENT_ID, USER_ID)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("When user is not admin Then throw exception")
    void whenUserIdInvalidThenThrowException() {
        var user = new GroupUser("any_user_id", UserPermission.ADMIN);
        var group = new Group(List.of(user));
        when(groupRepository.findByEventId(anyString())).thenReturn(Optional.of(group));

        assertThatThrownBy(() -> sut.remove(EVENT_ID, "invalid_user_id")).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("When requested to remove event Then delete event")
    void whenRequestedToRemoveEventThenDeleteEvent() {

        sut.remove(EVENT_ID, USER_ID);

        assertThat(eventRepository.getId()).isEqualTo(EVENT_ID);
        verify(groupRepository, times(1)).findByEventId(EVENT_ID);
        verify(eventRepository, times(1)).delete(EVENT_ID);
    }
}
