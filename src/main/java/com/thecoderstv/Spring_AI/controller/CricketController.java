package com.thecoderstv.Spring_AI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thecoderstv.Spring_AI.payload.CricketResponse;
import com.thecoderstv.Spring_AI.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/cricket")
public class CricketController {
    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<?> generateCricketResponse(@RequestParam String inputText) throws IOException {
        CricketResponse response = chatService.generateCricketResponse(inputText);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
