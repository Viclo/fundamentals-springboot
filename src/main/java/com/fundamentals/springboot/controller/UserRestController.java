package com.fundamentals.springboot.controller;

import com.fundamentals.springboot.caseuse.CreateUser;
import com.fundamentals.springboot.caseuse.DeleteUser;
import com.fundamentals.springboot.caseuse.GetUser;
import com.fundamentals.springboot.caseuse.UpdateUser;
import com.fundamentals.springboot.entity.User;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

  private GetUser getUser;
  private CreateUser createUser;
  private DeleteUser deleteUser;
  private UpdateUser updateUser;

  public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
    this.getUser = getUser;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
  }

  @GetMapping("/")
  List<User> getAllUsers() {
    return getUser.getAll();
  }

  @GetMapping("/list")
  List<User> getAllUsersWithPage(@RequestParam int page, @RequestParam int size) {
    return getUser.getWithPagination(page, size);
  }

  @PostMapping("/")
  ResponseEntity<User> addNewUser(@RequestBody User newUser) {
    return new ResponseEntity<>(createUser.saveNewUser(newUser), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  ResponseEntity deleteUser(@PathVariable Long id) {
    deleteUser.removeUser(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
    return new ResponseEntity<>(updateUser.updateUser(newUser, id), HttpStatus.OK);
  }
}
