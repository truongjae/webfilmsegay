package com.truongjae.webfilmsegay.controller;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
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
    public void addUser(@RequestBody UserSaveRequest userSaveRequest){
        userService.insert(userSaveRequest);
    }

    @GetMapping("/getall")
    public List<UserResponse> getAll(){
        return userService.findAll();
    }

}
