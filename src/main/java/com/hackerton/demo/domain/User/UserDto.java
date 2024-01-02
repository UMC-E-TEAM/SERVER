package com.hackerton.demo.domain.User;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private Long id;
    private String nickName;
}
