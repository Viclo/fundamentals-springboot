package com.fundamentals.springboot.bean;

public class MyTwoBeanImplement implements MyBean{

  @Override
  public void print() {
    System.out.println("Hello since my bean Two.");
  }
}
