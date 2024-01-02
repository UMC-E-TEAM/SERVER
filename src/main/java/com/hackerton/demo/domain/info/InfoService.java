package com.hackerton.demo.domain.info;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InfoService {

    private final InfoRepository infoRepository;

    public InfoDto getHome() {
        Random random = new Random();
        long randomNumber = random.nextLong(2)+1;

        Info info = infoRepository.getReferenceById(randomNumber);

        InfoDto infoDto = InfoDto.builder()
                .id(info.getId())
                .title(info.getTitle())
                .content(info.getContent())
                .build();

        return infoDto;
    }
}
