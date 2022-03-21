package com.truongjae.webfilmsegay.controller;

import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/adduser")
    public void addUser(@RequestBody User user){
        userService.insert(user);
    }

    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.findAll();
    }

}
