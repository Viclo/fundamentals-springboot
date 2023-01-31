package com.fundamentals.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency{

  @Override
  public void toGreet() {
    System.out.println("Hello since Two component");
  }
}
