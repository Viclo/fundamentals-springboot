package com.fundamentals.springboot.configuration;

import com.fundamentals.springboot.bean.MyBeanWithProperties;
import com.fundamentals.springboot.bean.MyBeanWithPropertiesImplement;
import com.fundamentals.springboot.pojo.UserPojo;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

  @Value("${value.firstname}")
  private String firstname;

  @Value("${value.lastname}")
  private String lastname;

  @Value("${value.random}")
  private String random;

  @Value("${jdbc.url}")
  private String jdbcUrl;

  @Value("${driver}")
  private String driver;

  @Value("${username}")
  private String username;

  @Value("${password}")
  private String password;

  @Bean
  public MyBeanWithProperties useValueProperties () {
    return new MyBeanWithPropertiesImplement(firstname, lastname);
  }

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(driver);
    dataSourceBuilder.url(jdbcUrl);
    dataSourceBuilder.username(username);
    dataSourceBuilder.password(password);
    return dataSourceBuilder.build();
  }
}
