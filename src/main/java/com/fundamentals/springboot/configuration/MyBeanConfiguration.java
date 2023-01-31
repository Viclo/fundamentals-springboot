package com.fundamentals.springboot.configuration;

import com.fundamentals.springboot.bean.MyBean;
import com.fundamentals.springboot.bean.MyBeanWithDependency;
import com.fundamentals.springboot.bean.MyBeanWithDependencyImplement;
import com.fundamentals.springboot.bean.MyOperation;
import com.fundamentals.springboot.bean.MyOperationImplement;
import com.fundamentals.springboot.bean.MyTwoBeanImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfiguration {
  @Bean
  public MyBean beanOperation() {
    return new MyTwoBeanImplement();
  }

  @Bean
  public MyOperation beanSumOperation() {
    return new MyOperationImplement();
  }

  @Bean
  public MyBeanWithDependency beanWithDependencyOperation(MyOperation myOperation) {
    return new MyBeanWithDependencyImplement(myOperation);
  }
}
