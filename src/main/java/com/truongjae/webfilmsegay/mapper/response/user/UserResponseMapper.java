package com.truongjae.webfilmsegay.mapper.response.user;

import com.truongjae.webfilmsegay.dto.response.user.UserResponse;
import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.mapper.Mapper;
import org.springframework.stereotype.Component;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserResponseMapper extends Mapper<User, UserResponse> {

}
