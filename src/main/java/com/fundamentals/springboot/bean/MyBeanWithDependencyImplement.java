package com.fundamentals.springboot.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

  private MyOperation myOperation;

  public MyBeanWithDependencyImplement (MyOperation myOperation) {
    this.myOperation = myOperation;
  }

  @Override
  public void printWithDependency() {
    int numberOne = 1;
    System.out.println(myOperation.sum(numberOne));
    System.out.println("Hello since Bean implementation with dependency.");
  }
}
