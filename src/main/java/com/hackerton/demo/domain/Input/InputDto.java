package com.hackerton.demo.domain.Input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InputDto {

    private Long id;

    private String content;
}
