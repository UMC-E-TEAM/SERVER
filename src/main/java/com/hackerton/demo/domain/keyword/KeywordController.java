package com.hackerton.demo.domain.keyword;

import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/keyword")
public class KeywordController {
    private final ChatService chatService;

    @GetMapping("/{userId}")
    @Operation(summary = "해당 유저의 오늘의 키워드 조회 ", description = "pathvariable로 들어오는 유저의 키워드를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GET_SUCCESS", description = "조회 성공"),
    })
    public ResponseEntity<ApiResponse<List<KeywordDto>>> getKeyword(@PathVariable(name = "userId") Long userId) {
        List<KeywordDto> keywordDtos = chatService.getKeywordList(userId);
        ApiResponse<List<KeywordDto>> apiResponse = new ApiResponse<>(ApiResponseStatus.GET_SUCCESS, keywordDtos);
        return ResponseEntity.ok(apiResponse);
    }

}
