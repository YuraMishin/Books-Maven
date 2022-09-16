package com.mishinyura.booksmaven.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean("myFirstBean")
    public String getHelloWorldString() {
        return "Hello, World from myFirstBean!";
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
