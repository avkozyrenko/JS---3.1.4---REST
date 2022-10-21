package com.example.REST314.service;

import com.example.REST314.model.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    User getUser(int id);

    public void updateUser(int id, User user);

    public void deleteUser(int id);

    public UserDetails loadUserByUsername(String username);


}
