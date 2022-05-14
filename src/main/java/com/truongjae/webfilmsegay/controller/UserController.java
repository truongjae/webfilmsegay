package com.truongjae.webfilmsegay.controller;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.exception.ObjectNotFoundException;
import com.truongjae.webfilmsegay.exception.UnauthorizedException;
import com.truongjae.webfilmsegay.security.JwtTokenUtil;
import com.truongjae.webfilmsegay.security.jwt.JwtRequest;
import com.truongjae.webfilmsegay.security.jwt.JwtResponse;
import com.truongjae.webfilmsegay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void register(@RequestBody UserSaveRequest userSaveRequest){
        userService.register(userSaveRequest);
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletRequest httpServletRequest) throws Exception {
        String username = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        // return token
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private String authenticate(String username, String password) throws Exception {
        User user = userService.findOneByUsername(username);
        if(user==null) throw new ObjectNotFoundException("Tên tài khoản không tồn tại");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED");
            throw new UnauthorizedException("Tài khoản đã bị khóa");
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Password không chính xác");
        }
        return username;
    }


    @GetMapping("/getall")
    public List<UserResponse> getAll(){
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
    }


}
