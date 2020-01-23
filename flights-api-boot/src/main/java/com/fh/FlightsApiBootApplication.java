package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.fh.mapper")
public class FlightsApiBootApplication {

 public static void main(String[] args) {
  SpringApplication.run(FlightsApiBootApplication.class, args);
 }

}
