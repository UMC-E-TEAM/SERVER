package com.hackerton.demo.domain.Result;

import com.hackerton.demo.domain.mapping.Keyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/search")
public class gptController {
    private final ChatService chatService;

//    @GetMapping("")
//    public String createGptComment(@RequestBody String content) {
//        return chatService.createGptComment(content);
//    }

    @GetMapping("/result")
    public ResponseEntity<List<Keyword>> createGptComment(@RequestBody String content) {
        List<Keyword> keywords = chatService.createGptComment(content);
        return ResponseEntity.ok(keywords);
    }
}