package com.mishinyura.booksmaven.configs;

import com.mishinyura.booksmaven.utils.constants.MainConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/" + MainConstants.BOOK_PHOTOS + "/**")
                .addResourceLocations("file:/" + Paths.get(MainConstants.BOOK_PHOTOS).toFile().getAbsolutePath() + "/");
    }
}
