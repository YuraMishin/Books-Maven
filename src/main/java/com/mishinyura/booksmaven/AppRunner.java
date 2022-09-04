package com.mishinyura.booksmaven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AppRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
        log.debug("Hello, World!");
    }

}
