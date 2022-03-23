package com.truongjae.webfilmsegay.mapper.request.user;

import com.truongjae.webfilmsegay.dto.request.user.UserSaveRequest;
import com.truongjae.webfilmsegay.entity.User;
import com.truongjae.webfilmsegay.mapper.Mapper;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserSaveRequestMapper extends Mapper<User, UserSaveRequest> {
}
