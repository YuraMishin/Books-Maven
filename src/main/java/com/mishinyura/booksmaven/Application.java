package com.mishinyura.booksmaven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Class Application.
 * Implements the java program entry point.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 04.09.2022
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("@PostConstruct example ok");
    }
}
