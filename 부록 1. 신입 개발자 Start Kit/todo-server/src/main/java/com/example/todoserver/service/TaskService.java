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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task add(String title, String description, LocalDate dueDate) {

        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();

        var saved = taskRepository.save(e);

        return entityToObject(saved);
    }

    public List<Task> getAll() {
        // entity 객체를 할 일 TASK 객체로 변환 -> 리스트 형태로 반환
        return taskRepository.findAll().stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<Task> getByDueDate(String dueDateStr) {
        Date dueDate = Date.valueOf(dueDateStr);
        return taskRepository.findAllByDueDate(dueDate).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<Task> getByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public Task getOne(Long id) {
        var entity = getById(id);
        return entityToObject(entity);
    }

    private TaskEntity getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("not exists task id [%d]", id)));
    }


    private Task entityToObject(TaskEntity entity) {
        LocalDateTime createdAt = Optional.ofNullable(entity.getCreatedAt())
                .map(Timestamp::toLocalDateTime)
                .orElse(null);

        LocalDateTime updatedAt = Optional.ofNullable(entity.getUpdatedAt())
                .map(Timestamp::toLocalDateTime)
                .orElse(null);

        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .dueDate(String.valueOf(entity.getDueDate()))
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}