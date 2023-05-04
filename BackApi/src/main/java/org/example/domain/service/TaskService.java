package org.example.domain.service;

import org.example.domain.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> all();
    List<TaskDto> getByUser(String email);
    TaskDto getById(int id);
    void deleteById(int id);

    void save(TaskDto taskDto);
}
