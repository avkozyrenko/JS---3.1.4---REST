package com.example.REST314.controller;

import com.example.REST314.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {


    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

}
