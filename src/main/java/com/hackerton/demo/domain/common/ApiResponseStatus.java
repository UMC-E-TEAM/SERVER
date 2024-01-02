package com.hackerton.demo.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseStatus {
    // 응답상태 기록
    SEARCH_SUCCESS(true, 201, "검색이 성공적으로 전달되었습니다.");

    private final boolean isSuccess;
    private final int status;
    private final String message;


}
