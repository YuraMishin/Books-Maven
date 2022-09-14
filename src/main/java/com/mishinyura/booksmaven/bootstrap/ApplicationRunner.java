package com.mishinyura.booksmaven.bootstrap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class ApplicationRunner implements CommandLineRunner {
    private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Hello, World!");
        log.debug(ctx.getId());
    }
}
