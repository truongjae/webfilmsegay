package com.truongjae.webfilmsegay.controller.viewuser;

import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class viewUserController {

    private final UserService userService;
//    @GetMapping("/home")
//    public String getAllUser(Model model){
//        List<UserResponse> userResponseList = userService.findAll();
//        model.addAttribute("listUser", userResponseList);
//        return "index";
//    }
    @GetMapping("/home")
    public ModelAndView getAllUser(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<UserResponse> userResponseList = userService.findAll();
        modelAndView.addObject("listUser",userResponseList);
        return modelAndView;
    }


}
