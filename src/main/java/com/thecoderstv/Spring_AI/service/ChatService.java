package com.thecoderstv.Spring_AI.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {
    @Autowired
    private ChatModel chatModel;

    @Autowired
    private StreamingChatModel streamingChatModel;

    public String generateResponse(String inputText) {
        return chatModel.call(inputText);
    }

    public Flux<String> generateStreamResponse(String inputText){
        return streamingChatModel.stream(inputText);
    }
}
