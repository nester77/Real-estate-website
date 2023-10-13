package com.nester.Rew.controller.view;

import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.user.UserDtoForSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthWebController {
    private static UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserDtoForSave user, Model model) {
        userService.registerUser(user);
        return "personal_page";
    }
}
