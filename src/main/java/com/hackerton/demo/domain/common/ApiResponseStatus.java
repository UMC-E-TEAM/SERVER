package com.hackerton.demo.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseStatus {
    // 응답상태 기록
    SEARCH_SUCCESS(true, 201, "검색이 성공적으로 전달되었습니다."),
    GET_SUCCESS(true, 202, "조회에 성공하였습니다"),
    EXIST_NICKNAME(true, 401, "중복된 닉네임입니다."),
    SIGN_IN_SUCCESS(true, 203, "회원가입 성공하였습니다");

    private final boolean isSuccess;
    private final int status;
    private final String message;


}
