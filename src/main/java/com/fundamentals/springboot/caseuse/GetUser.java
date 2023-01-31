package com.fundamentals.springboot.caseuse;

import com.fundamentals.springboot.entity.User;
import java.util.List;

public interface GetUser {

  List<User> getAll();

  List<User> getWithPagination(int page, int size);
}
