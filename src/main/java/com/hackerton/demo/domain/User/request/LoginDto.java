package com.hackerton.demo.domain.User.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;
}
