package org.example.domain.service;

import org.example.domain.dto.UserDto;
import org.postgresql.util.PSQLException;

import java.util.List;

public interface UserService {
    List<UserDto> all();
    UserDto getByCredentials(String name, String pass);

    void save(UserDto userDto) throws PSQLException;
}
