package com.truongjae.webfilmsegay.service;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.entity.User;

import java.util.List;

public interface UserService {
    void insert(UserSaveRequest userSaveRequest);

    List<UserResponse> findAll();

}
