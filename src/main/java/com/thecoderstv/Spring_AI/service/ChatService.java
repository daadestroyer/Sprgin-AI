package com.thecoderstv.Spring_AI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecoderstv.Spring_AI.payload.CricketResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private ChatModel chatModel;

    @Autowired
    private ImageModel imageModel;
    @Autowired
    private StreamingChatModel streamingChatModel;

    public String generateResponse(String inputText) {
        return chatModel.call(inputText);
    }

    public Flux<String> generateStreamResponse(String inputText) {
        return streamingChatModel.stream(inputText);
    }

    public CricketResponse generateCricketResponse(String inputText) throws IOException {
        String prompt = this.loadPromptTemplate("prompts/cricket_prompt.txt");
        String finalPrompt = this.putValuesInPromptTemplate(prompt, Map.of("inputText", inputText));

        ChatResponse cricketResponse = chatModel.call(new Prompt(finalPrompt));

        String responseText = cricketResponse.getResult().getOutput().getText();

        // Optional: Trim to ensure valid JSON starts at first '{'
        String json = responseText.substring(responseText.indexOf("{")).trim();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, CricketResponse.class);
    }

    public List<String> generateImages(String imageDescription, String size, int numberOfImages) throws IOException {
        String prompt = this.loadPromptTemplate("prompts/image_generation.txt");
        String finalPrompt = this.putValuesInPromptTemplate(prompt, Map.of(
                "numberOfImages", numberOfImages + " ",
                "description", imageDescription + " ",
                "size", size
        ));
        ImageResponse imageResponse = imageModel.call(new ImagePrompt(finalPrompt));
        return imageResponse.getResults().stream().map(generation -> generation.getOutput().getUrl()).collect(Collectors.toList());
    }

    public String loadPromptTemplate(String fileName) throws IOException {
        Path filePath = new ClassPathResource(fileName).getFile().toPath();
        return Files.readString(filePath);
    }

    public String putValuesInPromptTemplate(String template, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return template;
    }
}
