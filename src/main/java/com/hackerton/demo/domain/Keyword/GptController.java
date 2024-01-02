package com.hackerton.demo.domain.Keyword;

import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
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
    public ResponseEntity<ApiResponse<List<KeywordDto>>> createGptComment(@RequestParam String content) {
        List<KeywordDto> keywordDtos = chatService.createGptComment(content);
        ApiResponse<List<KeywordDto>> apiResponse = new ApiResponse<>(ApiResponseStatus.GET_SUCCESS, keywordDtos);
        return ResponseEntity.ok(apiResponse);
    }
}