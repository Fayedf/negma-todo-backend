package com.negma.Service;

import com.negma.DTO.TaskRequest;
import com.negma.DTO.TaskResponse;
import com.negma.Entity.Task;
import com.negma.Entity.User;
import com.negma.Mapper.TaskMapper;
import com.negma.Repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final CurrentUserService currentUserService;

    List<Task> deletedTasks = new ArrayList<>();

    private Task getOwnedTask(UUID uuid){
        User user = currentUserService.getCurrentUser();
        return taskRepo.findByIdAndUser(uuid,user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Task not found"));
    }


    public List<TaskResponse> getAllTasks() {
        User user = currentUserService.getCurrentUser();

        List<Task> tasks = taskRepo.findAllByUser(user);

        return tasks.stream().
                map(taskMapper::toResponse).toList();
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.toEntity(taskRequest);
        User user = currentUserService.getCurrentUser();
        task.setUser(user);
        task = taskRepo.save(task);
        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {

       Task task =  getOwnedTask(id);

       task.setTitle(taskRequest.getTitle());
       task.setDescription(taskRequest.getDescription());
       task.setPriority(taskRequest.getPriority());
       task.setDueDate(taskRequest.getDueDate());

       taskRepo.save(task);
       return taskMapper.toResponse(task);
    }

    public TaskResponse updateCompletionTask(UUID id) {

        Task task =  getOwnedTask(id);

        if(task.getCompleted()==true){
            task.setCompleted(false);
        }else {
            task.setCompleted(true);
        }
        taskRepo.save(task);
        return taskMapper.toResponse(task);
    }

    public TaskResponse DeleteById(UUID id) {

        Task task =  getOwnedTask(id);

        deletedTasks.add(task);
        taskRepo.deleteById(id);
        return taskMapper.toResponse(task);
    }
}
