package org.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.example.user", "org.example.music", "org.example.security"})
@EntityScan(basePackages = {"org.example.user", "org.example.music", "org.example.security"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
