package com.hackerton.demo.domain.info;

import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
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

    @GetMapping("/")
    public ResponseEntity<ApiResponse<InfoDto>> getHome() {
        ApiResponse<InfoDto> response = new ApiResponse<>(ApiResponseStatus.GET_SUCCESS, infoService.getHome());
        return ResponseEntity.ok(response);
    }
}
