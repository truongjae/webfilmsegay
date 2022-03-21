package com.truongjae.webfilmsegay.service.impl;

import com.truongjae.webfilmsegay.entity.Role;
import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.repository.UserRepository;
import com.truongjae.webfilmsegay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}


