package com.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.tutorial.persistence.entity.UserEntity;
import com.tutorial.persistence.repository.UserRepo;

@Service
public class UserService {

  @Autowired
  UserRepo userRepo;

  @Autowired
  PasswordEncoder passwordEncoder;

  /**
   * 新增帳號
   * @param User User data
   * @return UserEntity
   */
  public UserEntity createUser(UserEntity User){
    User.setPassword(passwordEncoder.encode(User.getPassword()));
    return userRepo.save(User);
  }

  /**
   * 新增帳號
   * @param User User data
   * @return UserEntity
   */
  public UserEntity getUser(UserEntity User){
    User.setPassword(passwordEncoder.encode(User.getPassword()));
    return userRepo.save(User);
  }

  /**
   * 取得單一帳號
   * @param id User id
   * @return UserEntity
   */
  public UserEntity getUser(long id){
    return userRepo.findById(id).orElse(null);
  }

  /**
   * 取得所有帳號
   * @return List<UserEntity>
   */
  public List<UserEntity> getUsers(){
    return userRepo.findAll();
  }


}
