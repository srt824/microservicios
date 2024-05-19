package com.example.usersservice.controller;

import com.example.usersservice.dto.UserDTO;
import com.example.usersservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public IUserService servUsu;

    @GetMapping("/posts/{user_id}")
    public UserDTO getUserAndPosts (@PathVariable Long user_id) {

        UserDTO user = servUsu.getUserAndPosts(user_id);

        return user;

    }
}
