package com.truongjae.webfilmsegay.dto.request.user;

import lombok.Data;

@Data
public class UserSaveRequest {
    private Long id;

    private String  username;

    private String password;

    private String fullName;
}
