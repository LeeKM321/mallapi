package com.spring.mallapi.service;

import com.spring.mallapi.dto.PageRequestDTO;
import com.spring.mallapi.dto.PageResponseDTO;
import com.spring.mallapi.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2024, 10, 10))
                .build();

        Long tno = todoService.register(todoDTO);

        log.info("tno: {}", tno);
    }

    @Test
    public void testGet() {
        Long tno = 101L;
        TodoDTO todoDTO = todoService.get(tno);
        log.info("{}", todoDTO);
    }

    @Test
    public void testDelete() {
        Long tno = 101L;
        todoService.remove(tno);

        TodoDTO todoDTO = todoService.get(tno);
        Assertions.assertNull(todoDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();

        PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);

        log.info("{}", response);
    }

}



















