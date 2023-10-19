package com.nester.Rew.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainWebController {

    @GetMapping("/home")
    public String createForm() {
        return "home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
