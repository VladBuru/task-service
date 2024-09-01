package com.task_service.task_service.event;

import lombok.Getter;

@Getter
public class UserDeletionFailedEvent {

    private final Long userId;

    public UserDeletionFailedEvent(Long userId) {
        this.userId = userId;
    }
}
