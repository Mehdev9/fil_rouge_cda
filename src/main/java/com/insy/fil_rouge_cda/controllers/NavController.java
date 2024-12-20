package com.insy.fil_rouge_cda.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class NavController {

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String propos() {
        return "propos";
    }
}
