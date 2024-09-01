package com.task_service.task_service.event;

import lombok.Getter;

@Getter
public class UserDeletedEvent {

    private final Long userId;

    public UserDeletedEvent(Long userId) {
        this.userId = userId;
    }
}
