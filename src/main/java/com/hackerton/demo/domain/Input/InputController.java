package com.hackerton.demo.domain.Input;

import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.hackerton.demo.domain.common.ApiResponseStatus.*;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
public class InputController {

    private final InputService inputService;

    // 사용자 질문 생성
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<InputDto>> getInputSearch(@RequestBody InputDto inputDto){

        inputService.saveInputSearch(inputDto);
        ApiResponse<InputDto> successResponse = new ApiResponse<>(SEARCH_SUCCESS);

        return ResponseEntity.status(OK).body(successResponse);
    }
}
