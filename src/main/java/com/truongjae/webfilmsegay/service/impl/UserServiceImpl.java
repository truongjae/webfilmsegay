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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserSaveRequestMapper userSaveRequestMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserSaveRequest userSaveRequest) {
        if(userRepository.existsByUsername(userSaveRequest.getUsername())){
            throw new BadRequestException("Username đã tồn tại");
        }
        Role role = roleRepository.findOneByName("ROLE_USER");
        User user = userSaveRequestMapper.to(userSaveRequest);
        user.setRoles(Arrays.asList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}


