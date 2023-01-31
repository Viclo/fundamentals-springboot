package com.fundamentals.springboot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user")
public class UserPojo {

  private String email;
  private String password;
  private int fundamentalNumbers;

  public UserPojo (String email, String password, int fundamentalNumbers) {
    this.email = email;
    this.password = password;
    this.fundamentalNumbers = fundamentalNumbers;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getFundamentalNumbers() {
    return fundamentalNumbers;
  }

  public void setFundamentalNumbers(int fundamentalNumbers) {
    this.fundamentalNumbers = fundamentalNumbers;
  }
}
