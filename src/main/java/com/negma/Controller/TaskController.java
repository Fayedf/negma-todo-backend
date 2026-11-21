package com.negma.Controller;

import com.negma.DTO.TaskRequest;
import com.negma.DTO.TaskResponse;
import com.negma.Entity.Task;
import com.negma.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        System.out.println(" task Request " + taskRequest.getTitle());
        TaskResponse newTask = taskService.createTask(taskRequest);
        return ResponseEntity.ok(newTask);

    }



}
