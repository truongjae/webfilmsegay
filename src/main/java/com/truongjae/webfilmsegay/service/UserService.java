package com.truongjae.webfilmsegay.service;

import com.truongjae.webfilmsegay.entity.User;

import java.util.List;

public interface UserService {
    void insert(User user);

    List<User> findAll();
}
