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

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    public List<TaskResponse> getAllTasks() {
      List<Task> tasks = taskRepo.findAll();

      List<TaskResponse> tasksResponse = tasks.stream().
              map(taskMapper::toResponse).toList();
        return tasksResponse;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.toEntity(taskRequest);
        System.out.printf("Task" + task.getTitle());
        task = taskRepo.save(task);
        return taskMapper.toResponse(task);
    }
}
