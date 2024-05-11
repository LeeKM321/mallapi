package com.spring.mallapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {

    private Long tno;
    private String title;
    private String writer;
    private boolean complete;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

}
