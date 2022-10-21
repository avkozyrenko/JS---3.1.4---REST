package com.example.REST314.controller;

import com.example.REST314.dto.UserDTO;
import com.example.REST314.model.User;
import com.example.REST314.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/api/user")
    public UserDTO getUser(Principal principal) {
        return convertToUserDTO(userService.loadUserByUsername(principal.getName()));

    }

    private UserDTO convertToUserDTO(UserDetails userDetails) {
        return modelMapper.map(userDetails, UserDTO.class);
    }
}
