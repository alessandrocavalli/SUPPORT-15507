package org.camunda.support.SUPPORT_15507;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("SUPPORT-15507")
public class CamundaApplication15507 {

  public static void main(String... args) {
    SpringApplication.run(CamundaApplication15507.class, args);
  }

}
