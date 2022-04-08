package com.truongjae.webfilmsegay.service.impl;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.entity.Role;
import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.exception.BadRequestException;
import com.truongjae.webfilmsegay.exception.OKException;
import com.truongjae.webfilmsegay.exception.ObjectNotFoundException;
import com.truongjae.webfilmsegay.mapper.request.user.UserSaveRequestMapper;
import com.truongjae.webfilmsegay.mapper.response.user.UserResponseMapper;
import com.truongjae.webfilmsegay.repository.RoleRepository;
import com.truongjae.webfilmsegay.repository.UserRepository;
import com.truongjae.webfilmsegay.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseMapper userResponseMapper;

    private final UserSaveRequestMapper userSaveRequestMapper;

    private final RoleRepository roleRepository;

    @Override
    public void register(UserSaveRequest userSaveRequest) {
        if(userRepository.existsByUsername(userSaveRequest.getUsername())){
            throw new BadRequestException("Username đã tồn tại");
        }
        Role role = roleRepository.findOneByName("ROLE_USER");
        User user = userSaveRequestMapper.to(userSaveRequest);
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        throw new OKException("Đăng kí tài khoản thành công");
    }

    @Override
    public List<UserResponse> findAll() {
        return userResponseMapper.from(userRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findOneById(id);
        if(user == null)
            throw new ObjectNotFoundException("Tài khoản không tồn tại");
        userRepository.deleteById(id);
        throw new OKException("Xóa thành công");
    }



}


