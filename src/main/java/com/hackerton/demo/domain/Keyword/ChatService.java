package com.hackerton.demo.domain.Keyword;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private OpenAiService openAiService;

    @Value("${chatgpt.key}")
    private String apiKey;
    private static final String MODEL = "gpt-3.5-turbo";

    private final KeywordRespository keywordRespository;

    public List<KeywordDto> createGptComment(String content) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(20));
        String prompt = content + "대신 딱 10개만 아무런 부가 설명 없이 키워드만 알려줘.";
        System.out.println(prompt);
        ChatCompletionRequest requester = ChatCompletionRequest.builder()
                .model(MODEL)
                .maxTokens(2048)
                .temperature(0.8)
                .messages(List.of(
                        new ChatMessage("user", prompt)
                )).build();

        ChatCompletionChoice chatCompletionResult = openAiService.createChatCompletion(requester).getChoices().get(0);
        String contentResult = chatCompletionResult.getMessage().getContent();

        return extractKeywords(contentResult);
    }

    private List<KeywordDto> extractKeywords(String content) {
        List<KeywordDto> keywordDtos = new ArrayList<>();

        // 개행 문자로 분할하여 리스트로 변환
        String[] keywordArray = content.split("\\r?\\n");

        for (String keywordTitle : keywordArray) {
            String cleanTitle = keywordTitle.replaceAll("\\d+\\.\\s*", "");
            KeywordDto keywordDto = KeywordDto.builder().optionTitle(cleanTitle).build();
            keywordDtos.add(keywordDto);
        }

        List<Keyword> keywords = keywordRespository.saveAll(convertToKeywords(keywordDtos));

        return convertToKeywordDtos(keywords);
    }

    private List<Keyword> convertToKeywords(List<KeywordDto> keywordDtos) {
        List<Keyword> keywords = new ArrayList<>();
        for (KeywordDto dto : keywordDtos) {
            Keyword keyword = new Keyword();
            keyword.setOptionTitle(dto.getOptionTitle());
            keywords.add(keyword);
        }
        return keywords;
    }

    private List<KeywordDto> convertToKeywordDtos(List<Keyword> keywords) {
        List<KeywordDto> keywordDtos = new ArrayList<>();
        for (Keyword keyword : keywords) {
            KeywordDto dto = KeywordDto.builder()
                    .optionTitle(keyword.getOptionTitle())
                    .build();
            keywordDtos.add(dto);
        }
        return keywordDtos;
    }
}