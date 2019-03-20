package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class DemoApplication {

  public static final String MEDIA_TYPE_CSV = "text/csv";

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
