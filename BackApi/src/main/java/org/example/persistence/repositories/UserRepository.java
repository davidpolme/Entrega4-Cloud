package org.example.persistence.repositories;

import org.example.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from users", nativeQuery = true)
    List<UserEntity> all();

    @Query(value = "select * from users where name = :name and password = :pass", nativeQuery = true)
    UserEntity getByCredentials(String name, String pass);

}
