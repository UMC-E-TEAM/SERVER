package com.hackerton.demo.domain.keyword;

import com.hackerton.demo.domain.User.User;
import com.hackerton.demo.domain.User.UserRepository;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.service.OpenAiService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;


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

    private final KeywordRespository keywordRespository;

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public List<KeywordDto> createGptComment(String content, Long userId) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(20));
        String prompt = content + "대신 딱 10개만 아무런 부가 설명 없이 키워드만 알려줘.";

        ChatCompletionRequest requester = ChatCompletionRequest.builder()
                .model(MODEL)
                .maxTokens(2048)
                .temperature(0.8)
                .messages(List.of(
                        new ChatMessage("user", prompt)
                )).build();

        ChatCompletionChoice chatCompletionResult = openAiService.createChatCompletion(requester).getChoices().get(0);
        String contentResult = chatCompletionResult.getMessage().getContent();

        return extractKeywords(contentResult, userId);
    }

    private List<KeywordDto> extractKeywords(String content, Long userId) {
        List<KeywordDto> keywordDtos = new ArrayList<>();

        // 개행 문자로 분할하여 리스트로 변환
        String[] keywordArray = content.split("\\r?\\n");

        for (String keywordTitle : keywordArray) {
            String cleanTitle = keywordTitle.replaceAll("\\d+\\.\\s*", "");
            KeywordDto keywordDto = KeywordDto.builder().optionTitle(cleanTitle).build();
            keywordDtos.add(keywordDto);
        }

        List<Keyword> keywords = keywordRespository.saveAll(convertToKeywords(keywordDtos, userId));

        return convertToKeywordDtos(keywords);
    }

    private List<Keyword> convertToKeywords(List<KeywordDto> keywordDtos, Long userId) {
        User user = userRepository.getReferenceById(userId);
        List<Keyword> keywords = new ArrayList<>();
        for (KeywordDto dto : keywordDtos) {
            Keyword keyword = new Keyword();
            keyword.setOptionTitle(dto.getOptionTitle());
            keyword.setUser(user);
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

    public List<PlaceDto> createGptPlace(String content) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(20));
        String prompt = content + "를 할 수 있는 대한민국에 있는 장소 5개만 부가 설명 없이 장소명만 알려줘. 장소명 말고 다른 설명은 필요 없어.";

        ChatCompletionRequest requester = ChatCompletionRequest.builder()
                .model(MODEL)
                .maxTokens(2048)
                .temperature(0.8)
                .messages(List.of(
                        new ChatMessage("user", prompt)
                )).build();

        ChatCompletionChoice chatCompletionResult = openAiService.createChatCompletion(requester).getChoices().get(0);
        String contentResult = chatCompletionResult.getMessage().getContent();

        return extractPlaces(contentResult);
    }

    private List<PlaceDto> extractPlaces(String content) {
        List<PlaceDto> placeDtos = new ArrayList<>();

        // 개행 문자로 분할하여 리스트로 변환
        String[] placeArray = content.split("\\r?\\n");

        for (String placeName : placeArray) {
            String cleanName = placeName.replaceAll("\\d+\\.\\s*", "");
            PlaceDto placeDto = PlaceDto.builder().placeName(cleanName).build();
            placeDtos.add(placeDto);
        }

        List<Place> places = placeRepository.saveAll(convertToPlace(placeDtos));

        return convertToPlaceDtos(places);
    }

    private List<Place> convertToPlace(List<PlaceDto> placeDtos) {
        List<Place> places = new ArrayList<>();
        for (PlaceDto dto : placeDtos) {
            Place place = new Place();
            place.setPlaceName(dto.getPlaceName());
            places.add(place);
        }
        return places;
    }

    private List<PlaceDto> convertToPlaceDtos(List<Place> places) {
        List<PlaceDto> placeDtos = new ArrayList<>();
        for (Place place : places) {
            PlaceDto dto = PlaceDto.builder()
                    .placeName(place.getPlaceName())
                    .build();
            placeDtos.add(dto);
        }
        return placeDtos;
    }

    public List<KeywordDto> getKeywordList(Long userId) {
        List<Keyword> keywords = keywordRespository.findAllByUserId(userId);
        List<KeywordDto> keywordDtos = keywords.stream()
                .map(keyword -> new KeywordDto(keyword.getOptionTitle()))
                .collect(Collectors.toList());

        return keywordDtos;
    }

}