package com.fundamentals.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {

  @Override
  public void toGreet() {
    System.out.println("Hello World since Component");
  }
}
