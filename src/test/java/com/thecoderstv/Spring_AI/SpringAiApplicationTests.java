package com.thecoderstv.Spring_AI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thecoderstv.Spring_AI.payload.CricketResponse;
import com.thecoderstv.Spring_AI.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class SpringAiApplicationTests {


	@Autowired
	private ChatService chatService;
	@Test
	void contextLoads() throws IOException {
		CricketResponse response = chatService.generateCricketResponse("who is sachin");
		System.out.println(response);
	}

	@Test
	void testTemplate() throws IOException {
		String s = chatService.loadPromptTemplate("prompts/cricket_prompt.txt");
		String s1 = chatService.putValuesInPromptTemplate(s, Map.of("inputText", "what is cricket"));
		System.out.println(s1);
	}
}
