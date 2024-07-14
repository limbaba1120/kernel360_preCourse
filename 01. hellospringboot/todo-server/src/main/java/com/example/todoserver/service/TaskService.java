package com.example.todoserver.service;

import com.example.todoserver.constants.TaskStatus;
import com.example.todoserver.persist.TaskRepository;
import com.example.todoserver.model.Task;
import com.example.todoserver.persist.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task add(String title, String description, LocalDateTime dueDate) {
        // LocalDateTime을 Timestamp로 변환
        Timestamp dueDateTimestamp = dueDate != null ? Timestamp.valueOf(dueDate) : null;

        // TaskEntity 생성
        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(dueDateTimestamp)
                .status(TaskStatus.TODO)
                .build();

        // 저장
        var saved = taskRepository.save(e);

        // Entity를 DTO로 변환
        return entityToObject(saved);
    }

    private Task entityToObject(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .dueDate(entity.getDueDate() != null ? entity.getDueDate().toLocalDateTime().toString() : null)
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .updatedAt(entity.getUpdatedAt().toLocalDateTime())
                .build();
    }
}