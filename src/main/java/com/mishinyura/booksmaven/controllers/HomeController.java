package com.mishinyura.booksmaven.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"", "/", "/index"})
    public String showHomePage() {
        return "index";
    }
}
