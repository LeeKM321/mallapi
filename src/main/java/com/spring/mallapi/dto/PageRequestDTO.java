package com.spring.mallapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter @ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

}
