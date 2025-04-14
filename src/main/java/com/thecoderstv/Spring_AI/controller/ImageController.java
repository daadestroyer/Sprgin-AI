package com.thecoderstv.Spring_AI.controller;

import com.thecoderstv.Spring_AI.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<String> generateImages(@RequestParam String imageDescription,
                                       @RequestParam(required = false, defaultValue = "500x500") String size,
                                       @RequestParam(required = false, defaultValue = "2") int numberOfImages) throws IOException {
        return chatService.generateImages(imageDescription, size, numberOfImages);
    }
}
