package com.hackerton.demo.domain.Input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InputService {

    private final InputRepository inputRepository;  // InputRespository 자동 주입

    public InputDto saveInputSearch(InputDto inputDto){

        Input input = inputRepository.findOne

    }

}
