package com.spring.mallapi.service;

import com.spring.mallapi.domain.Todo;
import com.spring.mallapi.dto.PageRequestDTO;
import com.spring.mallapi.dto.PageResponseDTO;
import com.spring.mallapi.dto.TodoDTO;
import com.spring.mallapi.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;

    public Long register(TodoDTO todoDTO) {
        log.info("......");

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        return savedTodo.getTno();
    }

    public TodoDTO get(Long tno)  {
        Todo todo = todoRepository.findById(tno).orElseThrow();

        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);

        return dto;
    }

    public void modify(TodoDTO todoDTO) {

        Todo todo = todoRepository.findById(todoDTO.getTno()).orElseThrow();

        todo.changeTitle(todoDTO.getTitle());
        todo.changeDueDate(todoDTO.getDueDate());
        todo.changeComplete(todoDTO.isComplete());

        todoRepository.save(todo);
    }

    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("tno").descending()
                );

        Page<Todo> result = todoRepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

    }



}


















