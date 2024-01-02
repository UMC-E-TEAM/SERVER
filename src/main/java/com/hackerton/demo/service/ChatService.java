package com.hackerton.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerton.demo.domain.mapping.Keyword;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private OpenAiService openAiService;

    @Value("${chatgpt.key}")
    private String apiKey;
    private static final String MODEL = "gpt-3.5-turbo";

    public List<Keyword> createGptComment(String content) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(20));
        String prompt = content + "앞선 얘기로 오늘 하루의 스케줄을 추천해줘. 대신 딱 10개만 아무런 부가 설명 없이 키워드만 알려줘.";
        System.out.println(prompt);
        ChatCompletionRequest requester = ChatCompletionRequest.builder()
                .model(MODEL)
                .maxTokens(2048)
                .messages(List.of(
                        new ChatMessage("user", prompt)
                )).build();

        ChatCompletionChoice chatCompletionResult = openAiService.createChatCompletion(requester).getChoices().get(0);
        String contentResult = chatCompletionResult.getMessage().getContent();

        return extractKeywords(contentResult);
    }

    private List<Keyword> extractKeywords(String content) {
        List<Keyword> keywords = new ArrayList<>();

        // 개행 문자로 분할하여 리스트로 변환
        String[] keywordArray = content.split("\\r?\\n");

        for (String keywordTitle : keywordArray) {
            Keyword keyword = new Keyword();
            String cleanTitle = keywordTitle.replaceAll("\\d+\\.\\s*", "");

            keyword.setOption(cleanTitle);
            keywords.add(keyword);
        }

        return keywords;
    }
}

