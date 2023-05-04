package org.example.domain.service.impl;

import org.example.domain.dto.TaskDto;
import org.example.domain.service.TaskService;
import org.example.persistence.entities.TaskEntity;
import org.example.persistence.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;
    @Override
    public List<TaskDto> all() {
        List<TaskDto> dtos = new ArrayList<>();
        List<TaskEntity> entities = repository.all();
        for (TaskEntity taskEntity: entities){
            TaskDto taskDto = TaskDto.builder().email(taskEntity.getEmail())
                    .id(taskEntity.getId()).fileName(taskEntity.getFileName())
                    .format(taskEntity.getFormat()).status(taskEntity.getStatus())
                    .time(taskEntity.getTime()).build();
            dtos.add(taskDto);
        }
        return dtos;
    }

    @Override
    public List<TaskDto> getByUser(String email) {
        List<TaskDto> dtos = new ArrayList<>();
        List<TaskEntity> entities = repository.getTasksByUser(email);
        for (TaskEntity taskEntity: entities){
            TaskDto taskDto = TaskDto.builder().email(taskEntity.getEmail())
                    .id(taskEntity.getId()).fileName(taskEntity.getFileName())
                    .format(taskEntity.getFormat()).status(taskEntity.getStatus())
                    .time(taskEntity.getTime()).build();
            dtos.add(taskDto);
        }
        return dtos;
    }

    @Override
    public TaskDto getById(int id) {
        TaskEntity taskEntity = repository.getById(id);
        TaskDto taskDto = TaskDto.builder().time(taskEntity.getTime())
                .status(taskEntity.getStatus()).format(taskEntity.getFormat())
                .fileName(taskEntity.getFileName())
                .email(taskEntity.getEmail())
                .id(taskEntity.getId()).build();
        return taskDto;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void save(TaskDto taskDto) {
        TaskEntity taskEntity = TaskEntity.builder().email(taskDto.getEmail())
                .time(taskDto.getTime()).format(taskDto.getFormat())
                .status(taskDto.getStatus()).fileName(taskDto.getFileName())
                .build();
        this.repository.save(taskEntity);
    }
}
