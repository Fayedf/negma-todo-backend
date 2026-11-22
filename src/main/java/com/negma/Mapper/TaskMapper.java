package com.negma.Mapper;

import com.negma.DTO.TaskRequest;
import com.negma.DTO.TaskResponse;
import com.negma.Entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.repository.query.parser.Part;

import javax.swing.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    @Mapping(target = "completed", ignore = true)
   Task toEntity(TaskRequest taskRequest);
    
    TaskResponse toResponse(Task  task);


}
