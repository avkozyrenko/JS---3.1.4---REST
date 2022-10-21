package com.example.REST314.controller;

import com.example.REST314.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }

}
