package com.hackerton.demo.domain.info;

import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/getHome")
    @Operation(summary = "홈 화면 오늘의 알쓸신잡 조회 API", description = "홈 화면에 띄워지는 알쓸신잡 정보를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GET_SUCCESS", description = "조회 성공"),
    })
    public ResponseEntity<ApiResponse<InfoDto>> getHome() {
        ApiResponse<InfoDto> response = new ApiResponse<>(ApiResponseStatus.GET_SUCCESS, infoService.getHome());
        return ResponseEntity.ok(response);
    }
}
