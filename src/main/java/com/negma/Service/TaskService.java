package com.negma.Service;

import com.negma.DTO.TaskRequest;
import com.negma.DTO.TaskResponse;
import com.negma.Entity.Task;
import com.negma.Mapper.TaskMapper;
import com.negma.Reposetory.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    List<Task> deletedTasks = new ArrayList<>();

    public List<TaskResponse> getAllTasks() {
      List<Task> tasks = taskRepo.findAll();

      List<TaskResponse> tasksResponse = tasks.stream().
              map(taskMapper::toResponse).toList();
        return tasksResponse;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.toEntity(taskRequest);
        task = taskRepo.save(task);
        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {
       Task task =  taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
       task.setTitle(taskRequest.getTitle());
       task.setDescription(taskRequest.getDescription());
       task.setPriority(taskRequest.getPriority());
       task.setDueDate(taskRequest.getDueDate());
       taskRepo.save(task);
       return taskMapper.toResponse(task);
    }

    public TaskResponse updateCompletionTask(UUID id) {
        Task task = taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        if(task.getCompleted()==true){
            task.setCompleted(false);
        }else {
            task.setCompleted(true);
        }
        taskRepo.save(task);
        return taskMapper.toResponse(task);
    }

    public TaskResponse DeleteById(UUID id) {
        Task task = taskRepo.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
        deletedTasks.add(task);
        taskRepo.deleteById(id);
        return taskMapper.toResponse(task);
    }
}
