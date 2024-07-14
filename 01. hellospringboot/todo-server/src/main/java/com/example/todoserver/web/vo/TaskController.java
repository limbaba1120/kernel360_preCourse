package com.example.todoserver.web.vo;

import com.example.todoserver.model.Task;
import com.example.todoserver.model.TaskRequest;
import com.example.todoserver.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     * @param request  추가하고자 하는 할일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request) {
        var result = taskService.add(
                request.getTitle(),
                request.getDescription(),
                request.getDueDate().atStartOfDay()
        );
        return ResponseEntity.ok(result);
    }
}
