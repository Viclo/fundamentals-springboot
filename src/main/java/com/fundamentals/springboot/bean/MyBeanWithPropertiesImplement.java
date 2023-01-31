package com.fundamentals.springboot.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties{

  private String firstname;
  private String lastname;

  public MyBeanWithPropertiesImplement (String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  @Override
  public StringBuilder useValueProperties() {
    return new StringBuilder(firstname).append(" ").append(lastname);
  }
}
