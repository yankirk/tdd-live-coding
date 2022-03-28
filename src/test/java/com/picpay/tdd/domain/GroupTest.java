package com.picpay.tdd.domain;

import com.picpay.tdd.domain.group.Group;
import com.picpay.tdd.domain.group.GroupUser;
import com.picpay.tdd.domain.group.UserPermission;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test Group Entity Domain")
class GroupTest {

    public static Stream<Arguments> provideUserPermission() {
        return Stream.of(
            Arguments.of(UserPermission.BASIC, false),
            Arguments.of(UserPermission.ADMIN, true),
            Arguments.of(UserPermission.OWNER, true));
    }

    @Test
    @DisplayName("When user id is invalid Then return false")
    void whenUserIdInvalidThenReturnFalse() {
        var user = new GroupUser("any_user_id", UserPermission.ADMIN);
        var group = new Group(List.of(user));

        var result = group.isAdmin("invalid_user_id");

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideUserPermission")
    @DisplayName("When check if user is admin Then return boolean")
    void whenCheckIfUserIsAdminThenReturnBoolean(UserPermission permission, boolean expected) {
        var user = new GroupUser("any_user_id", permission);
        var group = new Group(List.of(user));

        var result = group.isAdmin("any_user_id");

        assertThat(result).isEqualTo(expected);
    }

    /*@Test
    @DisplayName("When user permission is basic Then return false")
    void whenUserPermissionIsBasicThenReturnFalse() {
        var user = new GroupUser("any_user_id", UserPermission.BASIC);
        var group = new Group(List.of(user));

        var result = group.isAdmin("any_user_id");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("When user permission is admin Then return true")
    void whenUserPermissionIsAdminThenReturnTrue() {
        var user = new GroupUser("any_user_id", UserPermission.ADMIN);
        var group = new Group(List.of(user));

        var result = group.isAdmin("any_user_id");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("When user permission is owner Then return true")
    void whenUserPermissionIsOwnerThenReturnTrue() {
        var user = new GroupUser("any_user_id", UserPermission.OWNER);
        var group = new Group(List.of(user));

        var result = group.isAdmin("any_user_id");

        assertThat(result).isTrue();
    }*/
}