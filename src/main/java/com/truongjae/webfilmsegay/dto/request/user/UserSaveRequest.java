package com.truongjae.webfilmsegay.dto.request.user;

import lombok.Data;

@Data
public class UserSaveRequest {
    private String  username;

    private String password;

    private String fullName;
}
