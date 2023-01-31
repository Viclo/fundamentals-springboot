package com.fundamentals.springboot.bean;

public class MyBeanImplement implements MyBean{

  @Override
  public void print() {
    System.out.println("Hello since my bean.");
  }
}
