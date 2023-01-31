package com.fundamentals.springboot.caseuse;

import com.fundamentals.springboot.entity.User;
import com.fundamentals.springboot.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {

  private UserService userService;

  public UpdateUser(UserService userService) {
    this.userService = userService;
  }

  public User updateUser(User newUser, Long id) {
    return userService.updateUser(newUser, id);
  }
}
