package org.example.goaltracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.goaltracker.repository")
@EntityScan(basePackages = "org.example.goaltracker.model.entity")
public class GoalTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoalTrackerApplication.class, args);
    }

}
