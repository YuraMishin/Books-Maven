package com.mishinyura.booksmaven.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class BookBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
            log.debug("Hello from BookBootstrap.class");
    }
}
