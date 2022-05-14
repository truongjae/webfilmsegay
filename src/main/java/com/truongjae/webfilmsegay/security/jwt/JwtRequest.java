package com.truongjae.webfilmsegay.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class JwtRequest {

//    @NotEmpty(message = "Tên tài khoản không được để trống")
//    @NotBlank(message = "Tên tài khoản không được để trống")
    private String username;

//    @NotEmpty(message = "Mật khẩu không được để trống")
//    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}
