package com.fundamentals.springboot.caseuse;

import com.fundamentals.springboot.entity.User;
import com.fundamentals.springboot.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

  private UserService userService;

  public CreateUser(UserService userService) {
    this.userService = userService;
  }

  public User saveNewUser(User user) {
    return userService.save(user);
  }
}
