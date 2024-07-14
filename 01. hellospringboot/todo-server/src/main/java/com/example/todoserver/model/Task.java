package com.example.todoserver.model;

import com.example.todoserver.constants.TaskStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Task {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private String dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
