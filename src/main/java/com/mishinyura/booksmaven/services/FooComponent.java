package com.mishinyura.booksmaven.services;

import org.springframework.stereotype.Component;

@Component("fooComponent")
public class FooComponent {
    public String getMessage() {
        return "Hello, World from FooComponent!";
    }
}
