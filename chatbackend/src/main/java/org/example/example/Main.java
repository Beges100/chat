package org.example.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.example.repository")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);

    }

    @EventListener(ApplicationStartedEvent.class)
    public void appStated() {
        log.info("Приложение chat стартовало");
    }
}