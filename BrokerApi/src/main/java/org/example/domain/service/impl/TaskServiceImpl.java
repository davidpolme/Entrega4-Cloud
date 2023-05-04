package org.example.domain.service.impl;

import org.example.domain.dto.TaskDto;
import org.example.domain.service.TaskService;
import org.example.persistence.entities.TaskEntity;
import org.example.persistence.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(TaskDto taskDto) {
        TaskEntity taskEntity = TaskEntity.builder().email(taskDto.getEmail())
                .time(taskDto.getTime()).format(taskDto.getFormat())
                .status(taskDto.getStatus()).fileName(taskDto.getFileName())
                .build();
        this.taskRepository.save(taskEntity);
    }
}
