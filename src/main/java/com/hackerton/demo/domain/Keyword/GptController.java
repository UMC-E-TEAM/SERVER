package com.hackerton.demo.domain.Keyword;

import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/search")
public class GptController {
    private final ChatService chatService;

    @GetMapping("/result")
    @Operation(summary = "음석인식/텍스트 ChatGPT로 전달하여 키워드 추출", description = "음성인식/텍스트를 통해 ChatGPT 검색을 실행합니다. " + "검색 결과를 List로 반환하여 Keyword에 저장합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GET_SUCCESS", description = "조회 성공"),
    })
    public ResponseEntity<ApiResponse<List<KeywordDto>>> createGptComment(@RequestParam String content) {
        List<KeywordDto> keywordDtos = chatService.createGptComment(content);
        ApiResponse<List<KeywordDto>> apiResponse = new ApiResponse<>(ApiResponseStatus.GET_SUCCESS, keywordDtos);
        return ResponseEntity.ok(apiResponse);
    }
}