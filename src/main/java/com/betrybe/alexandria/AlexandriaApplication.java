package com.betrybe.alexandria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EntityScan("com.betrybe.alexandria.models.entities")
@EnableJpaRepositories("com.betrybe.alexandria.models.repositores")
@ComponentScan("com.betrybe.alexandria")
public class AlexandriaApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlexandriaApplication.class, args);
  }
}
