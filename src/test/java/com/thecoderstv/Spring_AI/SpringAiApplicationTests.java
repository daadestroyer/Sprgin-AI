package com.thecoderstv.Spring_AI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thecoderstv.Spring_AI.payload.CricketResponse;
import com.thecoderstv.Spring_AI.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiApplicationTests {


	@Autowired
	private ChatService chatService;
	@Test
	void contextLoads() throws JsonProcessingException {
		CricketResponse response = chatService.generateCricketResponse("who is sachin");
		System.out.println(response);
	}

}
