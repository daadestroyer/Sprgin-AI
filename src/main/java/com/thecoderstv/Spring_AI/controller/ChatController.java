package com.thecoderstv.Spring_AI.controller;

import com.thecoderstv.Spring_AI.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/v1")
    public ResponseEntity<String> generateResponse(@RequestParam String inputText) {
        String responseText = chatService.generateResponse(inputText);
        return new ResponseEntity<>(responseText, HttpStatus.OK);
    }

    @GetMapping("/v2")
    public Flux<String> generateStreamResponse(@RequestParam String inputText) {
        return chatService.generateStreamResponse(inputText);
    }
}