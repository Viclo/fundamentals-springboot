package com.fundamentals.springboot.configuration;

import com.fundamentals.springboot.caseuse.GetUser;
import com.fundamentals.springboot.caseuse.GetUserImplement;
import com.fundamentals.springboot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

  @Bean
  GetUser getUser(UserService userService) {
    return new GetUserImplement(userService);
  }
}
