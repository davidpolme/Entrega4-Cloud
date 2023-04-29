package org.example.domain.service.impl;

import antlr.Utils;
import org.apache.logging.log4j.message.Message;
import org.example.domain.dto.UserDto;
import org.example.domain.service.UserService;
import org.example.domain.utils.JwtUtil;
import org.example.persistence.entities.UserEntity;
import org.example.persistence.repositories.UserRepository;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> all() {
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.all();
        for(UserEntity userEntity: userEntities){
            UserDto userDto = UserDto.builder()
                    .id(userEntity.getId()).username(userEntity.getName())
                    .password1(userEntity.getPassword()).email(userEntity.getEmail())
                    .build();
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto getByCredentials(String name, String pass) {
        UserEntity userEntity = userRepository.getByCredentials(name, pass);
        return UserDto.builder().email(userEntity.getEmail())
                .id(userEntity.getId()).username(userEntity.getName())
                .password1("").password2("").token(JwtUtil.generateToken(userEntity)).build();
    }

    @Override
    public void save(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail()).name(userDto.getUsername())
                .password(userDto.getPassword1()).build();
        userRepository.save(userEntity);
    }
}
