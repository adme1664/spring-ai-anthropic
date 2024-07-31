package com.adme.learning.spring.ai.anthropic.conversation.configuration;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;
import com.adme.learning.spring.ai.anthropic.conversation.services.events.EventConsumerService;
import com.adme.learning.spring.ai.anthropic.conversation.services.events.EventSenderService;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class RabbitConfiguration {

  @Bean
  public Consumer<Event> messageConsumer(EventConsumerService eventConsumerService){
    return event -> {
      eventConsumerService.consumeEvent(event);
    };
  }

  @Bean
  public Supplier<Message<Event>> messageSupplier(EventSenderService eventSenderService){
    return eventSenderService::supplyEvent;
  }

}
