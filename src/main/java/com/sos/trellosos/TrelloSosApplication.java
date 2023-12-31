package com.sos.trellosos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TrelloSosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrelloSosApplication.class, args);
    }

}
