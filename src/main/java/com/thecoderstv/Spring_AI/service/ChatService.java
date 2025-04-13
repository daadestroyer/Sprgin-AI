package com.thecoderstv.Spring_AI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecoderstv.Spring_AI.payload.CricketResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
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

    public Flux<String> generateStreamResponse(String inputText) {
        return streamingChatModel.stream(inputText);
    }

    public CricketResponse generateCricketResponse(String inputText) throws JsonProcessingException {
        String promptString = "As a cricket expert, answer the following question: \"" + inputText + "\". " +
                "If the question is not related to cricket, reply with a cricket-related joke saying it's off-topic. " +
                "Respond in JSON format with a single key 'content' and your response as the value.";

        ChatResponse cricketResponse = chatModel.call(new Prompt(promptString));

        String responseText = cricketResponse.getResult().getOutput().getText();

        // Optional: Trim to ensure valid JSON starts at first '{'
        String json = responseText.substring(responseText.indexOf("{")).trim();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, CricketResponse.class);
    }
}
