package com.truongjae.webfilmsegay.service;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(UserSaveRequest userSaveRequest);

    List<UserResponse> findAll();

    void deleteById(Long id);

    User findOneByUsername(String username);
}
