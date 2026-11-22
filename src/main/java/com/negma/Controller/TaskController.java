package com.negma.Controller;

import com.negma.DTO.TaskRequest;
import com.negma.DTO.TaskResponse;
import com.negma.Entity.Task;
import com.negma.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor

@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class TaskController {

    private final TaskService taskService;

@GetMapping()
    public ResponseEntity<List<TaskResponse>> getTasks(){
       List<TaskResponse> tasks =  taskService.getAllTasks();
       return ResponseEntity.ok(tasks);
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
        TaskResponse newTask = taskService.createTask(taskRequest);
        return ResponseEntity.ok(newTask);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id,@RequestBody TaskRequest taskRequest){
        TaskResponse taskResponse = taskService.updateTask(id,taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateCompletionTask(@PathVariable UUID id){
        TaskResponse taskResponse = taskService.updateCompletionTask(id);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable UUID id){

        TaskResponse taskResponse = taskService.DeleteById(id);
        return ResponseEntity.ok(taskResponse);

    }


}
