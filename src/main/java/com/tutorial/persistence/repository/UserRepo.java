package com.tutorial.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tutorial.persistence.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Long> {

  UserEntity findByUserName(String userName);

}
