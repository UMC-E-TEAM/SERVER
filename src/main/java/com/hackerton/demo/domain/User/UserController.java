package com.hackerton.demo.domain.User;

import com.hackerton.demo.domain.User.request.LoginDto;
import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-ip")
    @Operation(summary = "기본 회원가입 API", description = "닉네임을 입력받아 회원가입을 진행합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "SIGN_IN_SUCCESS", description = "로그인 성공"),
    })
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody LoginDto request){
        ApiResponse<UserDto> response = new ApiResponse<>(ApiResponseStatus.SIGN_IN_SUCCESS, userService.login(request));
        return ResponseEntity.ok(response);
    }
}
