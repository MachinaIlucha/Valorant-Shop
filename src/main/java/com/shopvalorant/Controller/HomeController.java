package com.shopvalorant.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(path = "/")
    public String home() {
        return "index";
    }

    @GetMapping(path = "/login-page")
    public String loginPage() {
        return "login-page";
    }

    @GetMapping(path = "/registration-page")
    public String registrationPage() {
        return "registration-page";
    }
}
