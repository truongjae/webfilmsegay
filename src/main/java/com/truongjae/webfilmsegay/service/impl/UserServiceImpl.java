package com.truongjae.webfilmsegay.service.impl;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.mapper.request.user.UserSaveRequestMapper;
import com.truongjae.webfilmsegay.mapper.response.user.UserResponseMapper;
import com.truongjae.webfilmsegay.repository.UserRepository;
import com.truongjae.webfilmsegay.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseMapper userResponseMapper;

    private final UserSaveRequestMapper userSaveRequestMapper;

    @Override
    public void insert(UserSaveRequest userSaveRequest) {
        userRepository.save(userSaveRequestMapper.to(userSaveRequest));
    }

    @Override
    public List<UserResponse> findAll() {
        return userResponseMapper.from(userRepository.findAll());
    }

}


