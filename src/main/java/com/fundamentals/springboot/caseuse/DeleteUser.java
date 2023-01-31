package com.fundamentals.springboot.caseuse;

import com.fundamentals.springboot.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {

  private UserService userService;

  public DeleteUser(UserService userService) {
    this.userService = userService;
  }

  public void removeUser(Long id) {
    userService.delete(id);
  }
}
