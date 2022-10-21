package com.example.REST314.controller;

import com.example.REST314.dto.UserDTO;
import com.example.REST314.model.User;
import com.example.REST314.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class AdminRestController {

    private final UserService userservice;
    private final ModelMapper modelMapper;


    @Autowired
    public AdminRestController(UserService userservice, ModelMapper modelMapper) {
        this.userservice = userservice;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api/admin")
    public List<UserDTO> getAllUsers() {
        return userservice.getAllUsers().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("api/admin")
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody @Valid UserDTO userDTO) {
        userservice.addUser(convertToUser(userDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("api/admin/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable int id) {
        userservice.updateUser(id, convertToUser(userDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("api/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userservice.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
