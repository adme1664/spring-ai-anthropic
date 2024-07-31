package com.adme.learning.spring.ai.anthropic.conversation.configuration;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.anthropic.api.AnthropicApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnthropicConfiguration {

  @Bean
  public AnthropicChatModel chatModel(){
    AnthropicApi anthropicApi = new AnthropicApi(System.getenv("ANTHROPIC_API_KEY"));
    AnthropicChatModel chatModel = new AnthropicChatModel(anthropicApi,
        AnthropicChatOptions.builder()
            .withModel("claude-3-5-sonnet-20240620")
            .withTemperature(0.7f)
            .withMaxTokens(450)
            .build());

    return chatModel;

  }
}
