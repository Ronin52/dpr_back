package ru.ronin52.dpr.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ronin52.dpr.dto.AnswerDto;
import ru.ronin52.dpr.dto.KnowledgeBaseDto;
import ru.ronin52.dpr.service.KnowledgeBaseService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApplicationController {
    private final KnowledgeBaseService service;

    @GetMapping("/start")
    public KnowledgeBaseDto start(){
        return service.start();
    }

    @PostMapping("/answer")
    public KnowledgeBaseDto answer(@RequestBody AnswerDto answerDto) {
        return service.answer(answerDto.getPoint(), answerDto.isAnswer());
    }

}
