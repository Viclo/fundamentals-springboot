package com.fundamentals.springboot.caseuse;

import com.fundamentals.springboot.entity.User;
import com.fundamentals.springboot.service.UserService;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public class GetUserImplement implements GetUser{

  private UserService userService;

  public GetUserImplement(UserService userService) {
    this.userService = userService;
  }

  @Override
  public List<User> getAll() {
    return userService.getAllUsers();
  }

  @Override
  public List<User> getWithPagination(int page, int size) {
    return userService.findAll(PageRequest.of(page, size));
  }
}
