package com.adme.learning.spring.ai.anthropic.conversation;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiAnthropicConversation {

	public static void main(String[] args) {

		String anthropicApiKey = System.getenv("ANTHROPIC_API_KEY");

		Map.of("spring.ai.anthropic.api-key", anthropicApiKey).forEach(System::setProperty);

		SpringApplication.run(SpringAiAnthropicConversation.class, args);
	}

}
