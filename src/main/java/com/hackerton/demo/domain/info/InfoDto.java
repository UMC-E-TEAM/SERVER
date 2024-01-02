package com.hackerton.demo.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class InfoDto {
    private Long id;
    private String title;
    private String content;
}
