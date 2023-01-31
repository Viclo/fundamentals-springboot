package com.fundamentals.springboot.service;

import com.fundamentals.springboot.entity.User;
import com.fundamentals.springboot.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final Log LOG = LogFactory.getLog(UserService.class);
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public void saveTransactional(List<User> listUsers) {
    listUsers.stream()
        .peek(user -> LOG.info("User added: " + user))
        .forEach(userRepository::save);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.delete(new User(id));
  }

  public User updateUser(User user, Long id) {
    return userRepository.findById(id)
        .map(
            userAux -> {
              userAux.setEmail(user.getEmail());
              userAux.setBirthDate(user.getBirthDate());
              userAux.setName(user.getName());
               return userRepository.save(userAux);
            }
        ).orElseThrow(() -> new RuntimeException("User didn't find to modify. "));
  }

  public List<User> findAll(PageRequest of) {
    return userRepository.findAll(of).getContent();
  }
}
