package com.example.todoserver.web.vo;

import com.example.todoserver.constants.TaskStatus;
import com.example.todoserver.model.ResultResponse;
import com.example.todoserver.model.Task;
import com.example.todoserver.model.TaskRequest;
import com.example.todoserver.model.TaskStatusRequest;
import com.example.todoserver.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     *
     * @param request 추가하고자 하는 할일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request) {
        var result = taskService.add(
                request.getTitle(),
                request.getDescription(),
                request.getDueDate()
        );
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 마감일에 해당하는 할일 목록을 반환
     *
     * @param dueDate 할일의 마감일
     * @return 마감일에 해당하는 할일 목록
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTask(@RequestParam Optional<String> dueDate) {
        List<Task> result;

        if (dueDate.isPresent()) {
            result = taskService.getByDueDate(dueDate.get());
        } else {
            result = taskService.getAll();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 ID에 해당하는 할일을 조회
     *
     * @param id 할일 ID
     * @return ID 에 해당하는 할일 객체
     */
    @GetMapping("/status/{id}")
    public ResponseEntity<Task> fetchOneTask(@PathVariable Long id) {
        var result = taskService.getOne(id);
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 상태에 해당하는 할일 목록을 반환
     *
     * @param status 할일 상태
     * @return 상태에 해당하는 할일 목록
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getByStatus(@PathVariable TaskStatus status) {
        List<Task> result = taskService.getByStatus(status);
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 ID에 해당하는 할일을 수정
     *
     * @param id   할일 ID
     * @param task 수정할 할일 정보
     * @return 수정된 할일 객체
     */
    @PutMapping("/status/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody TaskRequest task) {
        Task result = taskService.update(id, task.getTitle(), task.getDescription(), task.getDueDate());

        return ResponseEntity.ok(result);
    }

    /**
     * 특정 ID에 해당하는 할일을 상태를 수정
     *
     * @param id   할일 ID
     * @param request 수정할 할일  상태 정보
     * @return 수정된 할일 객체
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id,
                                                 @RequestBody TaskStatusRequest request) {
        Task result = taskService.updateStatus(id, request.getStatus());

        return ResponseEntity.ok(result);
    }

    /**
     * 특정 ID에 해당하는 할일 삭제
     *
     * @param id 삭제할 할일 ID
     * @return 삭제 결과를 담은 응답 객체체
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteTask(@PathVariable Long id) {
        var result = taskService.delete(id);

        return ResponseEntity.ok(new ResultResponse(result));
    }

    @GetMapping("/status" )
    public ResponseEntity<TaskStatus[]> getAllStatus() {
        var status = TaskStatus.values();
        return ResponseEntity.ok(status);
    }
}
