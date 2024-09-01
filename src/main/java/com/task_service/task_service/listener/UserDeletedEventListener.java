package com.task_service.task_service.listener;

import com.task_service.task_service.event.UserDeletedEvent;
import com.task_service.task_service.event.UserDeletionFailedEvent;
import com.task_service.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDeletedEventListener {

    private final TaskService taskService;
    private final KafkaTemplate<Long, UserDeletionFailedEvent> kafkaTemplate;

    @Autowired
    public UserDeletedEventListener(TaskService taskService, KafkaTemplate<Long, UserDeletionFailedEvent> kafkaTemplate) {
        this.taskService = taskService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "user-deleted-topic", groupId = "user-service")
    public void consume(UserDeletedEvent event) {
        Long userId = event.getUserId();
        try {
            taskService.deleteUserIdForTasks(userId);
        } catch (Exception ex) {
            compensateUserDeletion(userId);
        }
    }

    private void compensateUserDeletion(Long userId) {
        UserDeletionFailedEvent event = new UserDeletionFailedEvent(userId);
        kafkaTemplate.send("user-deleted-failed", event);
    }
}
