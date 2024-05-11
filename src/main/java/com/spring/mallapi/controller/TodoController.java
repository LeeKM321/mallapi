package com.spring.mallapi.controller;

import com.spring.mallapi.dto.PageRequestDTO;
import com.spring.mallapi.dto.PageResponseDTO;
import com.spring.mallapi.dto.TodoDTO;
import com.spring.mallapi.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable Long tno) {

        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("{}", pageRequestDTO);

        return todoService.list(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
        log.info("TodoDTO: {}", todoDTO);

        Long tno = todoService.register(todoDTO);

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable Long tno,
            @RequestBody TodoDTO todoDTO
    ) {
        todoDTO.setTno(tno);
        log.info("Modify: {}", todoDTO);
        todoService.modify(todoDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno) {
        log.info("Remove: {}", tno);
        todoService.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }

}












